import React, { useEffect, useState } from 'react'
// @ts-ignore
import { Link as LinkTo } from 'react-router-dom'
import dayjs from 'dayjs'
import './index.css'
import { getPostsArchive } from '../../services'
import { ArchiveSkeleton } from '../../components/Skeleton'
import Pagination from '../../components/Pagination'

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
	/** 归档 */
	const [archives, setArchives] = useState<Array<Archive> | null>(null)
	/** 分页 */
	const [page, setPage] = useState<number>(1)
	/** 总页数 */
	const [pages, setPages] = useState<number>(0)

	/** 归档数据 */
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
		<div className='md:px-8 p-2 md:py-6'>
			<div className='mb-4 p-2 md:p-4 bg-white dark:bg-gray-700 rounded'>
				<div className='archive-list relative'>
					{archives?.map((archive: Archive) => {
						return (
							<div className='my-8 md:my-12' key={archive.date}>
								<div className='flex items-center archive-date'>
									<span className='flex-none text-2xl dark:text-gray-400'>
										{archive.date}
									</span>
								</div>
								<hr className='mt-4 md:mt-6' />
								{archive.posts.map((post: Post) => {
									return (
										<div
											className='flex justify-between items-center border-b border-dashed dark:border-gray-800 py-6 archive-post-item'
											key={post.id}
										>
											<span className='flex-none text-gray-400 dark:text-gray-500'>
												{dayjs(post.timestamp).format('MM-DD')}
											</span>
											<LinkTo
												to={'/post/' + post.id}
												className='flex flex-grow px-4 md:px-12 dark:text-gray-400 hover:text-gray-600 hover:underline'
											>
												{post.title}
											</LinkTo>
											<span className='flex justify-end flex-none text-sm text-gray-400 dark:text-gray-500'>
												{post.pageView}
											</span>
										</div>
									)
								})}
							</div>
						)
					}) || <ArchiveSkeleton />}
				</div>
			</div>
			{/* 分页 */}
			<Pagination pages={pages} page={page} setPage={setPage} />
		</div>
	)
}

export default PostPage
