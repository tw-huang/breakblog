import React, { useEffect, useRef, useState } from 'react'
import dayjs from 'dayjs'
import './index.css'
import Header from '../../compents/Header'
import Footer from '../../compents/Footer'
import PostImg from '../../assets/post.jpg'
import Banner from '../../compents/Banner'
import {
	fetchLinks,
	fetchCategories,
	fetchPostsHot,
	fetchPosts,
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

	// 监听搜索回车按键事件
	useEffect(() => {
		const handleSearchEvent = (event: { keyCode: number }) => {
			const { keyCode } = event
			if (keyCode === 13) {
				// 键盘回车键
				console.log(searchText)
			}
		}
		document.addEventListener('keyup', handleSearchEvent)
		return () => {
			document.removeEventListener('keyup', handleSearchEvent)
		}
	})

	const fetchData = async () => {
		const links = await fetchLinks()
		if (links?.success && links.code === 1) {
			setLinks(links.data)
		}
		const categories = await fetchCategories()
		if (categories?.success && categories.code === 1) {
			setCategories(categories.data)
		}
		const postsHot = await fetchPostsHot()
		if (postsHot?.success && postsHot.code === 1) {
			setPostsHot(postsHot.data)
		}
		const posts = await fetchPosts('', null, 1, 10)
		if (posts?.success && posts.code === 1) {
			setPosts(posts.data.records)
		}
	}

	useEffect(() => {
		//获取初始数据
		fetchData()
	}, [])

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
								<div className='flex md:pt-4 pt-2' key={post.id}>
									<div
										className='pr-2 hidden md:block md:w-1/3'
									>
										<img src={PostImg} alt='jpg'/>
									</div>
									<div className='flex flex-col md:w-2/3 w-full'>
										<a
											href='http://www.baidu.com'
											className='text-xl md:text-2xl pb-2'
										>
											{post.title}
										</a>
										<span className='text-xs pb-2 text-gray-500'>
											分类：{post.category.name} 日期：
											{dayjs(post.timestamp).format('YYYY-MM-DD')} 点击数：
											{post.pageView}
										</span>
										<span className='text-sm pb-2'>
											{post.subtitle}
										</span>
										<a
											href='http://www.baidu.com'
											className='text-sm text-gray-500'
											style={{ alignSelf: 'flex-end' }}
										>
											阅读正文-&gt;
										</a>
									</div>
								</div>
							)
						})}
					</div>
					{/* 分页 */}
					<div className='flex justify-between md:pt-8 pt-4 underline'>
						<a href='http://www.baidu.com'>←Prev</a>
						<a href='http://www.baidu.com'>Next→</a>
					</div>
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
						<div className='flex flex-col text-sm mt-1'>
							{categories.map((category: Category) => {
								return (
									<span className='pb-1' key={category.id}>
										<a href='http://www.baidu.com'>
											{category.name}({category.postCount})
										</a>
									</span>
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
