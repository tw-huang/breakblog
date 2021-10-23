import React, { useEffect, useState } from 'react'
// @ts-ignore
import { Link as LinkTo } from 'react-router-dom'
import dayjs from 'dayjs'
import Banner from '../../components/Banner'
import Sidebar from '../../components/Sidebar'
import Pagination from '../../components/Pagination'
import { HomeSkeleton } from '../../components/Skeleton'
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
	const [posts, setPosts] = useState<Array<Post> | null>(null)

	/** 处理搜索事件 */
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
									className={`hidden md:w-1/3 p-4 ${
										post.image ? 'md:block' : ''
									}`}
								>
									<img
										src={post.image}
										className='transform hover:scale-110 rounded-l'
										alt='文章配图'
									/>
								</div>
								<div
									className={`flex flex-col w-full md:p-4 p-2 ${
										post.image ? 'md:w-2/3' : ''
									}`}
								>
									<div className='pb-2 flex'>
										<LinkTo
											to={'/post/' + post.id}
											className='text-lg md:text-xl truncate dark:text-gray-400 hover:text-gray-600 hover:underline'
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
									<span className='text-xs italic text-gray-400 dark:text-gray-600 hover:underline self-end'>
										<LinkTo to={'/post/' + post.id}>正文-&gt;</LinkTo>
									</span>
								</div>
							</div>
						)
					}) || <HomeSkeleton />}
				</div>
				{/* 分页 */}
				<Pagination pages={pages} page={page} setPage={setPage} />
				<hr className='my-6 md:hidden' />
			</div>
			<Sidebar
				setCategoryId={setCategoryId}
				setPage={setPage}
				searchText={searchText}
				setSearchText={setSearchText}
				handleSearchEvent={handleSearchEvent}
			/>
		</div>
	)
}

export default Home
