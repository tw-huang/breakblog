import React, { useEffect, useState } from 'react'
// @ts-ignore
import { Link as LinkTo } from 'react-router-dom'
import dayjs from 'dayjs'
import './index.css'
import { getPost, getComments, postComment } from '../../services'
import { PostSkeleton, CommentSkeleton } from '../../components/Skeleton'
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

interface Comment {
	id: number
	createTime: string
	updateTime: string
	author: string
	email: string
	site: string
	body: string
	timestamp: string
	reviewed: boolean
	repliedId: number
	postId: number
	comment: Comment
}

/** 默认每页评论数量 */
const defaultPageSize: number = 2

const PostPage: React.FC = (props: any) => {
	const postId = props.match.params.id
	/** 文章 */
	const [post, setPost] = useState<Post | null>(null)
	/** 评论 */
	const [comments, setComments] = useState<Array<Comment> | null>(null)
	/** 评论分页 */
	const [page, setPage] = useState<number>(1)
	/** 评论总页数 */
	const [pages, setPages] = useState<number>(0)
	/** 回复评论 */
	const [replyComment, setReplyComment] = useState<Comment | null>(null)
	/** 昵称 */
	const [author, setAuthor] = useState<string>('')
	/** 邮件 */
	const [email, setEmail] = useState<string>('')
	/** 站点 */
	const [site, setSite] = useState<string>('')
	/** 内容 */
	const [body, setBody] = useState<string>('')

	/** 文章数据 */
	useEffect(() => {
		const fetchData = async () => {
			const post = await getPost(postId)
			if (post?.success && post.code === 1) {
				setPost(post.data)
				setPage(1)
			}
		}
		fetchData()
	}, [postId])

	/** 评论分页数据 */
	useEffect(() => {
		const fetchData = async () => {
			const comments = await getComments(postId, page, defaultPageSize)
			if (comments?.success && comments.code === 1) {
				setComments(comments.data.records)
				setPages(comments.data.pages)
			}
		}
		fetchData()
	}, [postId, page])

	/** 处理评论回复 */
	const handleSubmit = () => {
		if (body === null || body.replace(/(^\s*)|(\s*$)/g, '') === '') {
			alert('发表内容不能为空！')
			return
		}
		if (author === null || author.replace(/(^\s*)|(\s*$)/g, '') === '') {
			alert('昵称不能为空！')
			return
		}
		if (email === null || email.replace(/(^\s*)|(\s*$)/g, '') === '') {
			alert('邮件不能为空！')
			return
		}
		const fetchData = async () => {
			const data = await postComment(
				postId,
				author,
				email,
				site,
				body,
				replyComment?.id
			)
			if (data?.success && data.code === 1) {
				// 提交成功后重新获取最新评论数据
				const fetchData = async () => {
					const comments = await getComments(postId, page, defaultPageSize)
					if (comments?.success && comments.code === 1) {
						setComments(comments.data.records)
						setPages(comments.data.pages)
					}
				}
				fetchData()
				// 清空内容
				setReplyComment(null)
				setAuthor('')
				setEmail('')
				setBody('')
				setSite('')
			}
		}
		fetchData()
	}

	/** 文章内容 */
	function PostContent() {
		return (
			<>
				<span className='text-2xl mb-2 dark:text-gray-400'>{post?.title}</span>
				<span className='text-xs mb-2 text-gray-400 dark:text-gray-500'>
					分类：{post?.category?.name} 日期：
					{dayjs(post?.timestamp).format('YYYY-MM-DD HH:mm:ss')} 点击数：
					{post?.pageView}
				</span>
				<div className='p-2 md:p-4  bg-gray-50 dark:bg-gray-600 rounded'>
					<span className='text-sm dark:text-gray-500'>{post?.subtitle}</span>
				</div>
				<hr className='my-2 md:my-4' />
				<article
					className='leading-relaxed  max-w-none prose-sm md:prose md:max-w-none dark:text-gray-400 post-article'
					dangerouslySetInnerHTML={{ __html: post?.body || '' }}
				/>
				<hr className='my-2 md:my-4' />
				<div className='flex flex-nowrap justify-between py-4 md:py-8 text-xs md:text-sm'>
					<span className='w-1/2 flex flex-row pr-2 md:pr-4'>
						{post?.nextPostId === null ? (
							<span className='text-gray-600 dark:text-gray-500'>
								上一篇：这已经是最新的文章啦！
							</span>
						) : (
							<LinkTo
								to={'/post/' + post?.nextPostId}
								className='truncate hover:text-gray-600 dark:text-gray-400 hover:underline'
							>
								上一篇：{post?.nextPostTitle}
							</LinkTo>
						)}
					</span>
					<span className='w-1/2 flex flex-row-reverse pl-2 md:pl-4'>
						{post?.prevPostId === null ? (
							<span className='text-gray-600 dark:text-gray-500'>
								下一篇：这已经是最初的文章啦！
							</span>
						) : (
							<LinkTo
								to={'/post/' + post?.prevPostId}
								className='truncate hover:text-gray-600 dark:text-gray-400 hover:underline'
							>
								下一篇：{post?.prevPostTitle}
							</LinkTo>
						)}
					</span>
				</div>
			</>
		)
	}

	/** 评论回复 */
	function CommentForm() {
		return (
			<div className='p-2 md:p-4 bg-white dark:bg-gray-700 rounded'>
				<span className='text-lg dark:text-gray-400'>
					{replyComment === null
						? '发表评论'
						: `回复 ${replyComment?.author} : ${replyComment?.body}`}
				</span>
				<div className='py-4'>
					<div className='flex flex-col'>
						<div className='flex flex-col md:flex-row'>
							<div className='flex items-center w-full md:w-1/2 py-2 md:pr-2'>
								<span className='text-center text-sm leading-10 h-10 w-12 bg-gray-100 dark:bg-gray-600 rounded-l'>
									昵 称
								</span>
								<input
									type='text'
									className='h-10 w-full placeholder-gray-300 dark:placeholder-gray-400 border border-gray-300 dark:border-gray-500 dark:bg-gray-600 focus:outline-none focus:ring-1 ring-gray-400 rounded-r px-2 py-1'
									placeholder='twhuang'
									value={author}
									onChange={(event) => setAuthor(event.target.value)}
								/>
							</div>

							<div className='flex items-center w-full md:w-1/2 py-2 md:pl-2'>
								<span className='text-center text-sm leading-10 h-10 w-12 bg-gray-100 dark:bg-gray-600 rounded-l'>
									邮 件
								</span>
								<input
									type='text'
									className='h-10 w-full placeholder-gray-300 dark:placeholder-gray-400 border border-gray-300 dark:border-gray-500 dark:bg-gray-600 focus:outline-none focus:ring-1 ring-gray-400 rounded-r px-2 py-1'
									placeholder='tw.huang@foxmail.com'
									value={email}
									onChange={(event) => setEmail(event.target.value)}
								/>
							</div>
						</div>

						<div className='flex items-center w-full py-2'>
							<span className='text-center text-sm leading-10 h-10 w-12 bg-gray-100 dark:bg-gray-600 rounded-l'>
								站 点
							</span>
							<input
								type='text'
								className='h-10 w-full placeholder-gray-300 dark:placeholder-gray-400 border border-gray-300 dark:border-gray-500 dark:bg-gray-600 focus:outline-none focus:ring-1 ring-gray-400 rounded-r px-2 py-1'
								placeholder='https://twhuang.top'
								value={site}
								onChange={(event) => setSite(event.target.value)}
							/>
						</div>

						<div className='flex w-full py-2'>
							<span className='text-center text-sm leading-10 h-10 w-12 bg-gray-100 dark:bg-gray-600 rounded-l'>
								内 容
							</span>
							<textarea
								className='h-10 w-full placeholder-gray-300 dark:placeholder-gray-400 border border-gray-300 dark:border-gray-500 dark:bg-gray-600 focus:outline-none focus:ring-1 ring-gray-400 rounded-r px-2 py-1'
								placeholder='写得不错，学习了！'
								value={body}
								onChange={(event) => setBody(event.target.value)}
							/>
						</div>
						<button
							type='submit'
							className='h-10 bg-gray-800 text-white rounded my-2 hover:bg-gray-600'
							onClick={handleSubmit}
						>
							发 表
						</button>
					</div>
				</div>
			</div>
		)
	}

	/** 评论列表内容 */
	function CommentContent() {
		return (
			<div className='flex flex-col'>
				<hr className='my-2 md:my-4' />
				<div className='p-2 md:p-4 bg-white dark:bg-gray-700 rounded'>
					<span className='text-xl dark:bg-gray-700 dark:text-gray-400'>
						评论
					</span>
					<div className='py-4'>
						{comments == null ? (
							<CommentSkeleton />
						) : (
							<>
								{/* 评论列表 */}
								{comments?.length === 0 ? (
									<span className='dark:text-gray-400'>
										暂无评论，尝试发表你的意见？
									</span>
								) : (
									comments?.map((comment: Comment) => {
										return comment.repliedId === null ? (
											<div
												className='flex flex-col py-2 border-b dark:border-gray-800 border-dashed'
												key={comment.id}
											>
												<div className='flex justify-between items-center'>
													<div className='flex items-center'>
														<span className='text-gray-600 dark:text-gray-500'>
															{comment.author}
														</span>
														<span className='text-gray-400 dark:text-gray-500 text-sm mx-4 '>
															|
														</span>
														<span className='text-sm text-gray-400 dark:text-gray-500'>
															{dayjs(comment.timestamp).format(
																'YYYY-MM-DD HH:mm:ss'
															)}{' '}
														</span>
													</div>
													<div
														className='flex items-center text-sm text-gray-500'
														style={{ alignSelf: 'flex-end' }}
													>
														<span className='rounded-full h-6 w-6 flex items-center justify-center bg-gray-50 dark:bg-gray-600 dark:text-gray-500 mr-2 text-xs'>
															{comment.id}
														</span>
														<button
															className='focus:outline-none hover:text-gray-600 hover:underline'
															onClick={() => {
																setReplyComment(comment)
															}}
														>
															回复
														</button>
													</div>
												</div>
												<span className='py-3 px-4 text-gray-600'>
													{comment.body}
												</span>
											</div>
										) : (
											<div
												className='flex flex-col py-2 border-b dark:border-gray-800 border-dashed'
												key={comment.id}
											>
												<div className='flex justify-between items-center'>
													<div className='flex items-center'>
														<span className='text-gray-600 dark:text-gray-500'>
															{comment.author}
														</span>
														<span className='text-gray-400 dark:text-gray-500 text-sm mx-4 '>
															|
														</span>
														<span className='text-sm text-gray-400 dark:text-gray-500'>
															{dayjs(comment.timestamp).format(
																'YYYY-MM-DD HH:mm:ss'
															)}{' '}
														</span>
													</div>
													<div
														className='flex items-center text-sm text-gray-500'
														style={{ alignSelf: 'flex-end' }}
													>
														<span className='rounded-full h-6 w-6 flex items-center justify-center bg-gray-50 dark:bg-gray-600 dark:text-gray-500 mr-2 text-xs'>
															{comment.id}
														</span>
														<button
															className='focus:outline-none hover:text-gray-600 hover:underline'
															onClick={() => {
																setReplyComment(comment)
															}}
														>
															回复
														</button>
													</div>
												</div>
												<span className='mt-3 mx-8 text-gray-600'>
													@{comment.comment?.author} : {comment.comment?.body}
												</span>
												<span className='py-3 px-4 text-gray-600'>
													{comment.body}
												</span>
											</div>
										)
									})
								)}
							</>
						)}
					</div>
					{/* 分页 */}
					<Pagination pages={pages} page={page} setPage={setPage} />
				</div>
				<hr className='my-2 md:my-4' />
				<CommentForm />
			</div>
		)
	}

	return (
		<div className='md:px-8 p-2 md:py-6'>
			<div className='flex flex-col p-2 md:p-4 bg-white dark:bg-gray-700 rounded'>
				{post == null ? <PostSkeleton /> : <PostContent />}
			</div>
			{post?.canComment ? <CommentContent /> : ''}
		</div>
	)
}

export default PostPage
