import React, { useContext, useEffect, useRef, useState } from 'react'
// @ts-ignore
import { Link as LinkTo } from 'react-router-dom'
import AppContext from '../../store'
import {
	getBlogStatistic,
	getCategories,
	getLinks,
	getPostsHot,
} from '../../services'
import dayjs from 'dayjs'

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

interface BlogStatistic {
	posts: number
	pageviews: number
	categories: number
}

interface BlogInfo {
	name: string
	avatar: string
	email: string
	blogTitle: string
	blogSubtitle: string
}

interface Props {
	setCategoryId: any
	setPage: any
	searchText: string
	setSearchText: any
}

const Sidebar: React.FC<Props> = (props) => {
	/** 上下文 */
	// @ts-ignore
	const context = useContext<BlogInfo>(AppContext)
	/** 博客统计 */
	const [blogStatistic, setBlogStatistic] = useState<BlogStatistic | null>(null)
	/** 友链 */
	const [links, setLinks] = useState<Array<Link> | null>(null)
	/** 分类 */
	const [categories, setCategories] = useState<Array<Category> | null>(null)
	/** 热门文章 */
	const [postsHot, setPostsHot] = useState<Array<Post> | null>(null)

	/** 侧边栏数据 */
	useEffect(() => {
		const fetchBlogStatistic = async () => {
			const blogStatistic = await getBlogStatistic()
			if (blogStatistic?.success && blogStatistic.code === 1) {
				setBlogStatistic(blogStatistic.data)
			}
		}
		const fetchPostsHot = async () => {
			const postsHot = await getPostsHot()
			if (postsHot?.success && postsHot.code === 1) {
				setPostsHot(postsHot.data)
			}
		}
		const fetchCategories = async () => {
			const categories = await getCategories()
			if (categories?.success && categories.code === 1) {
				setCategories(categories.data)
			}
		}
		const fetchLinks = async () => {
			const links = await getLinks()
			if (links?.success && links.code === 1) {
				setLinks(links.data)
			}
		}
		fetchBlogStatistic()
		fetchPostsHot()
		fetchCategories()
		fetchLinks()
	}, [])

	return (
		<div className='md:pr-8 md:pl-0 px-2 md:w-1/4 md:py-6'>
			{/* 搜索栏 */}
			<div className='relative'>
				<input
					id='searchInput'
					type='text'
					className='w-full h-10 pl-8 pr-2 py-1 dark:text-gray-400 bg-gray-50 dark:bg-gray-700 placeholder-gray-300 dark:placeholder-gray-400 border border-gray-300 dark:border-gray-600 focus:outline-none focus:ring-1 ring-gray-400 rounded'
					placeholder='tips: 文章标题回车搜索'
					value={props.searchText}
					onChange={(event) => props.setSearchText(event.target.value)}
				/>
				<span className='absolute bottom-3 left-2 focus:outline-none text-sm'>
					<svg
						d='1631728156124'
						className='icon'
						viewBox='0 0 1024 1024'
						version='1.1'
						xmlns='http://www.w3.org/2000/svg'
						p-id='7031'
						width='16'
						height='16'
					>
						<path
							d='M928.256 879.232l-146.688-143.402667-3.413333-5.248a34.389333 34.389333 0 0 0-48.512 0c-124.672 114.346667-316.714667 120.576-448.810667 14.506667-132.096-106.026667-163.242667-291.413333-72.789333-433.194667 90.453333-141.781333 274.432-195.968 429.952-126.634666 155.52 69.333333 234.24 240.725333 184.064 400.469333a33.237333 33.237333 0 0 0 7.765333 32.896 35.072 35.072 0 0 0 33.024 9.898667 34.261333 34.261333 0 0 0 25.301333-23.04c59.989333-189.525333-30.634667-393.514667-213.333333-480C492.117333 39.04 272.256 96.042667 157.44 259.626667 42.538667 423.253333 67.2 644.352 215.466667 779.946667c148.224 135.552 375.466667 144.896 534.826666 21.973333l129.877334 126.976a34.944 34.944 0 0 0 48.469333 0 33.450667 33.450667 0 0 0 0-47.786667l-0.341333-1.877333z'
							fill='#9CA3AF'
							p-id='7032'
						/>
					</svg>
				</span>
			</div>
			{/* 网站信息 */}
			<div className='w-full mt-6'>
				<div className='text-center text-sm'>
					<div className='flex justify-center items-center w-32 h-32 mx-auto bg-white dark:bg-gray-700 rounded-full transition duration-1000 ease-in-out hover:bg-gray-100  dark:hover:bg-gray-800 transform hover:rotate-180'>
						<img
							src={context?.avatar}
							alt=''
							className='w-28 h-28'
							style={{ borderRadius: '50%' }}
						/>
					</div>
					<div className='mt-2 dark:text-gray-400'>
						<span className='text-sm'>{context?.name}</span>
						<br />
						<span className='text-xs'>{context?.blogSubtitle}</span>
					</div>
				</div>
			</div>
			{/* 博客统计 */}
			<div className='flex mt-6 py-2 justify-between text-center text-gray-600 dark:text-gray-500 text-xs bg-white dark:bg-gray-700 rounded'>
				<span className='w-1/3'>
					文章 <br /> {blogStatistic?.posts}
				</span>
				<span className='w-1/3'>
					分类 <br /> {blogStatistic?.categories}
				</span>
				<span className='w-1/3'>
					浏览量 <br /> {blogStatistic?.pageviews}
				</span>
			</div>

			{/* 热门文章 */}
			<div className='mt-6 bg-white dark:bg-gray-700 rounded'>
				<div className='font-medium bg-gray-200 dark:bg-gray-800 dark:text-gray-400 opacity-75 px-2 py-1 rounded-t'>
					热门文章:
				</div>
				<div className='text-sm p-4'>
					{postsHot?.map((post: Post) => {
						return (
							<div
								className='flex flex-col border-b border-dashed dark:border-gray-800 pb-1 mb-2'
								key={post.id}
							>
								<LinkTo
									to={'/post/' + post.id}
									className='text-gray-600 dark:text-gray-500 mb-1 truncate hover:text-gray-600 hover:underline'
								>
									{post.title}
								</LinkTo>
								<span className='text-xs text-gray-400 dark:text-gray-600'>
									日期：{dayjs(post.timestamp).format('YYYY-MM-DD')} 点击数：
									{post.pageView}
								</span>
							</div>
						)
					})}
				</div>
			</div>
			{/* 文章分类 */}
			<div className='mt-6 bg-white dark:bg-gray-700 rounded'>
				<div className='font-medium bg-gray-200 dark:bg-gray-800 dark:text-gray-400 opacity-75 px-2 py-1 rounded-t'>
					文章分类:
				</div>
				<div className='text-sm text-gray-600 dark:text-gray-500 p-4'>
					<div
						className='flex justify-between mb-2 cursor-pointer border-b border-dashed dark:border-gray-800 hover:text-gray-600 hover:underline'
						onClick={() => {
							props.setCategoryId(null)
							props.setPage(1)
						}}
					>
						<span>全部</span>
						<span className='rounded-full h-4 w-4 text-xs flex items-center justify-center bg-gray-50 dark:bg-gray-600 dark:text-gray-500'>
							{categories?.length || 0}
						</span>
					</div>
					{categories?.map((category: Category) => {
						return (
							<div
								className='flex justify-between mb-2 cursor-pointer border-b border-dashed dark:border-gray-800 hover:text-gray-600 hover:underline'
								key={category.id}
								onClick={() => {
									props.setCategoryId(category.id)
									props.setPage(1)
								}}
							>
								<span>{category.name}</span>
								<span className='rounded-full h-4 w-4 text-xs flex items-center justify-center bg-gray-50 dark:bg-gray-600 dark:text-gray-500'>
									{category.postCount}
								</span>
							</div>
						)
					})}
				</div>
			</div>
			{/* 友情链接 */}
			<div className='my-6 bg-white dark:bg-gray-700 rounded'>
				<div className='font-medium bg-gray-200 dark:bg-gray-800 dark:text-gray-400 opacity-75 px-2 py-1 rounded-t'>
					友情链接:
				</div>
				<div className='flex flex-col text-sm text-gray-600 dark:text-gray-500 p-4'>
					{links?.map((link: Link) => {
						return (
							<a
								key={link.id}
								href={link.url}
								className='flex justify-between items-center mb-2 border-b border-dashed dark:border-gray-800 hover:text-gray-600 hover:underline'
								target='_blank'
								rel='noopener noreferrer'
							>
								<span>{link.name}</span>
								<span>-&gt;</span>
							</a>
						)
					})}
				</div>
			</div>
		</div>
	)
}

export default Sidebar
