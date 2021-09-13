import React, { useEffect, useState } from 'react'
// @ts-ignore
import { Link as LinkTo } from 'react-router-dom'
import dayjs from 'dayjs'
import './index.css'
import Header from '../../compents/Header'
import Footer from '../../compents/Footer'
import { getPostsArchive } from '../../services'

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
	prevPostId: number
	prevPostTitle: string
	nextPostId: number
	nextPostTitle: string
}

interface Archive {
	date: string
	posts: Array<Post>
}

/** 默认每页文章数量 */
const defaultPageSize: number = 15

const PostPage: React.FC = () => {
	// 归档
	const [archives, setArchives] = useState<Array<Archive>>([])
	// 分页
	const [page, setPage] = useState<number>(1)
	// 总页数
	const [pages, setPages] = useState<number>(0)

	// 初始数据
	useEffect(() => {
		const fetchData = async () => {
			const posts = await getPostsArchive(page, defaultPageSize)
			if (posts?.success && posts.code === 1) {
				setPages(posts.data.pages)
				setArchives(posts.data.records)
			}
		}
		fetchData()
	}, [page])

	return (
		<div className='md:max-w-screen-lg w-full md:my-8 md:mx-auto bg-gray-100 shadow rounded'>
			<Header />
			<div className='md:px-8 p-2 md:py-6'>
				<div className='mb-4 p-2 md:p-4 bg-white rounded'>
					{archives.map((archive: Archive) => {
						return (
							<div className='my-8 md:my-12'>
								<span className='text-2xl font-medium'>{archive.date}</span>
								<hr className='mt-4 md:mt-6' />
								{archive.posts.map((post: Post) => {
									return (
										<div className='flex justify-between items-center border-b border-dashed py-6'>
											<span className='flex-none text-gray-400'>
												{dayjs(post.timestamp).format('MM-DD')}
											</span>
											<LinkTo
												to={'/post/' + post.id}
												className='flex flex-grow px-4 md:px-12 font-medium hover:text-gray-400 hover:underline'
											>
												{post.title}
											</LinkTo>
											<span className='flex justify-end flex-none text-sm text-gray-400'>
												{post.pageView}
											</span>
										</div>
									)
								})}
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
			</div>
			<Footer />
		</div>
	)
}

export default PostPage
