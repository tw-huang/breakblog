import React, { useEffect, useRef, useState } from 'react'
// @ts-ignore
import { Link as LinkTo } from 'react-router-dom'
import dayjs from 'dayjs'
import './index.css'
import Banner from '../../compents/Banner'
import Sidebar from '../../compents/Sidebar'
import { getPosts } from '../../services'

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

/** 默认每页文章数量 */
const defaultPageSize: number = 6

const Home: React.FC = () => {
	/** 搜索文本 */
	const [searchText, setSearchText] = useState<string>('')
	/** 分页 */
	const [page, setPage] = useState<number>(1)
	/** 分类 ID */
	const [categoryId, setCategoryId] = useState<number | null>(null)
	/** 总页数 */
	const [pages, setPages] = useState<number>(0)
	/** 文章 */
	const [posts, setPosts] = useState<Array<Post> | null>([])

	/** 监听搜索回车按键事件 */
	useEffect(() => {
		const handleSearchEvent = (event: { keyCode: number }) => {
			const { keyCode } = event
			// 仅当焦点在 searchInput 上，回车事件才执行查询
			// @ts-ignore
			if (keyCode === 13 && document.activeElement.id === 'searchInput') {
				const text = searchText.replace(/(^\s*)|(\s*$)/g, '')
				const fetchData = async () => {
					const posts = await getPosts(text, null, 1, defaultPageSize)
					if (posts?.success && posts.code === 1) {
						setPosts(posts.data.records)
						setPages(posts.data.pages)
					}
				}
				fetchData()
			}
		}
		document.addEventListener('keyup', handleSearchEvent)
		return () => {
			document.removeEventListener('keyup', handleSearchEvent)
		}
	})

	/** 文章分页数据 */
	useEffect(() => {
		const fetchData = async () => {
			const posts = await getPosts('', categoryId, page, defaultPageSize)
			if (posts?.success && posts.code === 1) {
				setPosts(posts.data.records)
				setPages(posts.data.pages)
			}
		}
		fetchData()
	}, [page, categoryId])

	return (
		<div className='flex flex-col md:flex-row'>
			<div className='md:px-8 p-2 md:w-3/4 md:py-6'>
				{/* 轮播图 */}
				<Banner />
				{/* 文章列表 */}
				<div>
					{posts?.map((post: Post) => {
						return (
							<div
								className='flex md:my-4 my-2 bg-white dark:bg-gray-700 rounded'
								key={post.id}
							>
								<div
									className={`hidden md:w-1/3 ${post.image ? 'md:block' : ''}`}
								>
									<img src={post.image} className='rounded-l' alt='文章配图' />
								</div>
								<div
									className={`flex flex-col w-full md:p-4 p-2 ${
										post.image ? 'md:w-2/3' : ''
									}`}
								>
									<div className='pb-2 flex'>
										<LinkTo
											to={'/post/' + post.id}
											className='text-lg md:text-xl font-medium truncate dark:text-gray-400 hover:text-gray-600 hover:underline'
										>
											{post.title}
										</LinkTo>
									</div>
									<span className='text-xs pb-2 text-gray-400 dark:text-gray-600'>
										分类：{post.category.name} 日期：
										{dayjs(post.timestamp).format('YYYY-MM-DD')} 点击数：
										{post.pageView}
									</span>
									<span className='text-sm pb-2 text-gray-600 dark:text-gray-500'>
										{post.subtitle}
									</span>
									<span className='text-xs text-gray-400 dark:text-gray-600 hover:underline self-end'>
										<LinkTo to={'/post/' + post.id}>阅读正文-&gt;</LinkTo>
									</span>
								</div>
							</div>
						)
					})}
				</div>
				{/* 分页 */}
				{pages <= 1 ? (
					''
				) : (
					<div className='flex justify-between items-center mt-4 md:mt-6 font-medium bg-white dark:bg-gray-700 dark:text-gray-400 hover:text-gray-600 rounded'>
						<button
							className={`p-2  focus:outline-none ${
								page <= 1 ? 'disabled:opacity-50' : 'hover:underline'
							}`}
							onClick={() => setPage(page - 1)}
							disabled={page <= 1}
						>
							←Prev
						</button>
						<span className='text-gray-400 text-sm'>
							{page} / {pages}
						</span>
						<button
							className={`p-2 focus:outline-none ${
								page >= pages ? 'disabled:opacity-50' : 'hover:underline'
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
			<Sidebar
				setCategoryId={setCategoryId}
				setPage={setPage}
				searchText={searchText}
				setSearchText={setSearchText}
			/>
		</div>
	)
}

export default Home
