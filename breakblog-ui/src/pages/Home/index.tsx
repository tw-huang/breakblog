import React, { useEffect, useRef, useState } from 'react'
// @ts-ignore
import { Link as LinkTo } from 'react-router-dom'
import dayjs from 'dayjs'
import './index.css'
import AvatarImg from '../../assets/avatar.png'
import Header from '../../compents/Header'
import Footer from '../../compents/Footer'
import Banner from '../../compents/Banner'
import {
	getBlogInfo,
	getLinks,
	getCategories,
	getPostsHot,
	getPosts,
} from '../../services'

interface Link {
	id: number
	createTime: string
	updateTime: string
	name: string
	url: string
}

interface Category {
	id: number
	createTime: string
	updateTime: string
	name: string
	postCount: number
}

interface Post {
	id: number
	createTime: string
	updateTime: string
	title: string
	subtitle: string
	image: string
	body: string
	timestamp: string
	canComment: boolean
	pageView: number
	category: Category
}

interface BlogInfo {
	posts: number
	pageviews: number
	categories: number
}

const Home: React.FC = () => {
	// 搜索文本
	const [searchText, setSearchText] = useState<string>('')
	// 搜索文本 ref
	const searchEl = useRef(null)
	// 博客信息
	const [blogInfo, setBlogInfo] = useState<BlogInfo>()
	// 友链
	const [links, setLinks] = useState<Array<Link>>([])
	// 分类
	const [categories, setCategories] = useState<Array<Category>>([])
	// 热门文章
	const [postsHot, setPostsHot] = useState<Array<Post>>([])
	// 文章
	const [posts, setPosts] = useState<Array<Post>>([])
	// 分页
	const [page, setPage] = useState<number>(1)
	// 总页数
	const [pages, setPages] = useState<number>(0)
	// 分类 ID
	const [categoryId, setCategoryId] = useState<any>(null)

	// 监听搜索回车按键事件
	useEffect(() => {
		const handleSearchEvent = (event: { keyCode: number }) => {
			const { keyCode } = event
			if (keyCode === 13) {
				// 键盘回车键
				const text = searchText.replace(/(^\s*)|(\s*$)/g, '')
				if (text !== undefined && text !== null && text !== '') {
					const fetchData = async () => {
						const posts = await getPosts(searchText, null, 1, 5)
						if (posts?.success && posts.code === 1) {
							setPosts(posts.data.records)
							setPages(posts.data.pages)
						}
					}
					fetchData()
					// setSearchText('')
				}
			}
		}
		document.addEventListener('keyup', handleSearchEvent)
		return () => {
			document.removeEventListener('keyup', handleSearchEvent)
		}
	})

	// 侧边栏数据
	useEffect(() => {
		const fetchData = async () => {
			const blogInfo = await getBlogInfo()
			if (blogInfo?.success && blogInfo.code === 1) {
				setBlogInfo(blogInfo.data)
			}
			const postsHot = await getPostsHot()
			if (postsHot?.success && postsHot.code === 1) {
				setPostsHot(postsHot.data)
			}
			const categories = await getCategories()
			if (categories?.success && categories.code === 1) {
				setCategories(categories.data)
			}
			const links = await getLinks()
			if (links?.success && links.code === 1) {
				setLinks(links.data)
			}
		}
		//获取初始数据
		fetchData()
	}, [])

	// 文章分页数据
	useEffect(() => {
		const fetchData = async () => {
			const posts = await getPosts('', categoryId, page, 5)
			if (posts?.success && posts.code === 1) {
				setPosts(posts.data.records)
				setPages(posts.data.pages)
			}
		}
		fetchData()
	}, [page, categoryId])

	return (
		<div className='md:max-w-screen-lg w-full md:my-8 md:mx-auto bg-gray-100 shadow rounded'>
			{/* 顶部 */}
			<Header />
			{/* 内容 */}
			<div className='flex flex-col md:flex-row'>
				<div className='md:px-8 p-2 md:w-3/4 md:py-6'>
					{/* 轮播图 */}
					<Banner />
					{/* 文章列表 */}
					<div>
						{posts.map((post: Post) => {
							return (
								<div
									className='flex md:my-4 my-2 bg-white rounded'
									key={post.id}
								>
									<div
										className={`pr-2 hidden md:w-1/3 ${
											post.image ? 'md:block' : ''
										}`}
									>
										<img src={post.image} className='rounded' alt='文章配图' />
									</div>
									<div
										className={`flex flex-col w-full md:p-4 p-2 ${
											post.image ? 'md:w-2/3' : ''
										}`}
									>
										<div className='pb-2 flex'>
											<LinkTo
												to={'/post/' + post.id}
												className='text-lg md:text-xl font-medium truncate hover:text-gray-400 hover:underline'
											>
												{post.title}
											</LinkTo>
										</div>
										<span className='text-xs pb-2 text-gray-400'>
											分类：{post.category.name} 日期：
											{dayjs(post.timestamp).format('YYYY-MM-DD')} 点击数：
											{post.pageView}
										</span>
										<span className='text-sm pb-2 text-gray-600'>
											{post.subtitle}
										</span>
										<LinkTo
											to={'/post/' + post.id}
											className='text-sm text-gray-400'
											style={{ alignSelf: 'flex-end' }}
										>
											阅读正文-&gt;
										</LinkTo>
									</div>
								</div>
							)
						})}
					</div>
					{/* 分页 */}
					{pages <= 1 ? (
						''
					) : (
						<div className='flex justify-between pt-4'>
							<button
								className={`p-2 font-medium focus:outline-none ${
									page <= 1
										? 'disabled:opacity-50'
										: 'hover:text-gray-400 hover:underline'
								}`}
								onClick={() => setPage(page - 1)}
								disabled={page <= 1}
							>
								←Prev
							</button>
							<button
								className={`p-2 font-medium focus:outline-none ${
									page >= pages
										? 'disabled:opacity-50'
										: 'hover:text-gray-400 hover:underline'
								}`}
								onClick={() => setPage(page + 1)}
								disabled={page >= pages}
							>
								Next→
							</button>
						</div>
					)}
					<hr className='my-6 md:hidden' />
				</div>
				<div className='md:pr-8 md:pl-0 px-2 md:w-1/4 md:py-6'>
					{/* 搜索栏 */}
					<div>
						<label>
							<input
								type='text'
								className='w-full placeholder-gray-300 border border-gray-300 focus:outline-none focus:ring-1 ring-gray-400 rounded px-2 py-1'
								placeholder='tips: 输入文章标题搜索'
								value={searchText}
								onChange={(event) => setSearchText(event.target.value)}
								ref={searchEl}
							/>
						</label>
					</div>
					{/* 网站信息 */}
					<div className='w-full mt-6'>
						<div className='text-center text-sm'>
							<div className='flex justify-center items-center w-32 h-32 mx-auto bg-white rounded-full'>
								<img
									src={AvatarImg}
									alt='avatar'
									className='w-28 h-28 '
									style={{ borderRadius: '50%' }}
								/>
							</div>
							<div className='mt-2'>最难的是控制自己</div>
						</div>
						<div className='flex md:mt-4 mt-2 py-2 justify-between text-center text-gray-600 text-xs bg-white rounded'>
							<span className='w-1/3'>
								文章 <br /> {blogInfo?.posts}
							</span>
							<span className='w-1/3'>
								分类 <br /> {blogInfo?.categories}
							</span>
							<span className='w-1/3'>
								浏览量 <br /> {blogInfo?.pageviews}
							</span>
						</div>
					</div>
					{/* 热门文章 */}
					<div className='mt-6 bg-white rounded'>
						<div className='font-medium bg-gray-200 opacity-75 px-2 py-1 rounded-t'>
							热门文章:
						</div>
						<div className='text-sm p-4'>
							{postsHot.map((post: Post) => {
								return (
									<div className='flex flex-col mb-2' key={post.id}>
										<LinkTo
											to={'/post/' + post.id}
											className='text-gray-600 mb-1 truncate hover:text-gray-400 hover:underline'
										>
											{post.title}
										</LinkTo>
										<span className='text-xs text-gray-400'>
											日期：{dayjs(post.timestamp).format('YYYY-MM-DD')}{' '}
											点击数：{post.pageView}
										</span>
									</div>
								)
							})}
						</div>
					</div>
					{/* 文章分类 */}
					<div className='mt-6 bg-white rounded'>
						<div className='font-medium bg-gray-200 opacity-75 px-2 py-1 rounded-t'>
							文章分类:
						</div>
						<div className='text-sm text-gray-600 p-4'>
							<div
								className='flex justify-between mb-2 cursor-pointer border-b hover:text-gray-400 hover:underline'
								onClick={() => {
									setCategoryId(null)
								}}
							>
								<span>全部(All)</span>
								<span className='rounded-full h-6 w-6 flex items-center justify-center bg-gray-50'>{categories.length || 0}</span>
							</div>
							{categories.map((category: Category) => {
								return (
									<div
										className='flex justify-between mb-2 cursor-pointer border-b hover:text-gray-400 hover:underline'
										key={category.id}
										onClick={() => {
											setCategoryId(category.id)
											setPage(1)
										}}
									>
										<span>{category.name}</span>
										<span className='rounded-full h-6 w-6 flex items-center justify-center bg-gray-50'>{category.postCount}</span>
									</div>
								)
							})}
						</div>
					</div>
					{/* 友情链接 */}
					<div className='mt-6 bg-white rounded'>
						<div className='font-medium bg-gray-200 opacity-75 px-2 py-1 rounded-t'>
							友情链接:
						</div>
						<div className='flex flex-col text-sm text-gray-600 p-4'>
							{links.map((link: Link) => {
								return (
									<span
										className='mb-2 hover:text-gray-400 hover:underline'
										key={link.id}
									>
										<span>-&gt;&nbsp;</span>
										<a
											href={link.url}
											target='_blank'
											rel='noopener noreferrer'
										>
											{link.name}
										</a>
									</span>
								)
							})}
						</div>
					</div>
				</div>
			</div>
			{/* 尾部 */}
			<Footer />
		</div>
	)
}

export default Home
