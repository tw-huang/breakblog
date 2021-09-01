import React, { useEffect, useRef, useState } from 'react'
// @ts-ignore
import { Link as LinkTo } from 'react-router-dom'
import dayjs from 'dayjs'
import './index.css'
import Header from '../../compents/Header'
import Footer from '../../compents/Footer'
import PostImg from '../../assets/post.jpg'
import Banner from '../../compents/Banner'
import {
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
	body: string
	timestamp: string
	canComment: boolean
	pageView: number
	category: Category
}

const Home: React.FC = () => {
	// 搜索文本
	const [searchText, setSearchText] = useState<string>('')
	// 搜索文本 ref
	const searchEl = useRef(null)
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
				if (
					text !== undefined &&
					text !== null &&
					text !== ''
				) {
					const fetchData = async () => {
						const posts = await getPosts(searchText, null, 1, 5)
						if (posts?.success && posts.code === 1) {
							setPosts(posts.data.records)
							setPages(posts.data.pages)
						}
					}
					fetchData()
					setSearchText('')
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
			const links = await getLinks()
			if (links?.success && links.code === 1) {
				setLinks(links.data)
			}
			const categories = await getCategories()
			if (categories?.success && categories.code === 1) {
				setCategories(categories.data)
			}
			const postsHot = await getPostsHot()
			if (postsHot?.success && postsHot.code === 1) {
				setPostsHot(postsHot.data)
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

	// 回到首页初始状态
	const indexClick = () => {
		console.log('indexClick')
	}

	return (
		<div className='md:max-w-screen-lg w-full md:my-8 md:mx-auto bg-white '>
			{/* 顶部 */}
			<Header />
			<div className='flex flex-col md:flex-row'>
				<div className='md:px-8 p-2 md:w-3/4 md:py-6'>
					{/* 轮播图 */}
					<Banner />
					{/* 文章列表 */}
					<div>
						{posts.map((post: Post) => {
							return (
								<div className='flex md:my-4 my-2' key={post.id}>
									<div className='pr-2 hidden md:block md:w-1/3'>
										<img src={PostImg} alt='jpg' />
									</div>
									<div className='flex flex-col md:w-2/3 w-full'>
										<LinkTo
											to={'/post/' + post.id}
											className='text-xl md:text-2xl pb-2'
										>
											{post.title}
										</LinkTo>
										<span className='text-xs pb-2 text-gray-500'>
											分类：{post.category.name} 日期：
											{dayjs(post.timestamp).format('YYYY-MM-DD')} 点击数：
											{post.pageView}
										</span>
										<span className='text-sm pb-2'>{post.subtitle}</span>
										<LinkTo
											to={'/post/' + post.id}
											className='text-sm text-gray-500'
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
					{pages === 1 ? (
						''
					) : (
						<div className='flex justify-between md:pt-8 pt-4'>
							<button
								className='p-2'
								onClick={() => setPage(page - 1)}
								disabled={page <= 1}
							>
								←Prev
							</button>
							<button
								className='p-2'
								onClick={() => setPage(page + 1)}
								disabled={page >= pages}
							>
								Next→
							</button>
						</div>
					)}
				</div>
				<div className='md:pr-8 md:pl-0 px-2 md:w-1/4 md:py-6'>
					{/* 搜索栏 */}
					<div>
						<label>
							<input
								type='text'
								className='w-full border-2 border-back'
								placeholder='请输入搜索的关键词'
								value={searchText}
								onChange={(event) => setSearchText(event.target.value)}
								ref={searchEl}
							/>
						</label>
					</div>
					{/* 热门文章 */}
					<div className='md:mt-4 mt-2'>
						<span className='text-lg'>热门文章:</span>
						<div className='flex flex-col text-sm mt-1'>
							{postsHot.map((post: Post) => {
								return (
									<div className='flex flex-col pb-1' key={post.id}>
										<a href='http://www.baidu.com' className='pb-1'>
											{post.title}
										</a>
										<span className='text-xs text-gray-500'>
											日期：{dayjs(post.timestamp).format('YYYY-MM-DD')}{' '}
											点击数：{post.pageView}
										</span>
									</div>
								)
							})}
						</div>
					</div>
					{/* 文章分类 */}
					<div className='md:mt-4 mt-2'>
						<span className='text-lg'>文章分类:</span>
						<div className='text-sm mt-1'>
							<div
								className='pb-1 cursor-pointer'
								onClick={() => {
									setCategoryId(null)
								}}
							>
								<span>All</span>
							</div>
							{categories.map((category: Category) => {
								return (
									<div
										className='pb-1 cursor-pointer'
										key={category.id}
										onClick={() => {
											setCategoryId(category.id)
											setPage(1)
										}}
									>
										<span>
											{category.name}({category.postCount})
										</span>
									</div>
								)
							})}
						</div>
					</div>
					{/* 友情链接 */}
					<div className='md:mt-4 mt-2'>
						<span className='text-lg'>友情链接:</span>
						<div className='flex flex-col text-sm mt-1'>
							{links.map((link: Link) => {
								return (
									<span className='pb-1' key={link.id}>
										<a href={link.url}>{link.name}</a>
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
