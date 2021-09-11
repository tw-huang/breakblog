import React, { useEffect, useState } from 'react'
// @ts-ignore
import { Link as LinkTo } from 'react-router-dom'
import dayjs from 'dayjs'
import './index.css'
import Header from '../../compents/Header'
import Footer from '../../compents/Footer'
import { getPost, getComments, postComment } from '../../services'

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

const PostPage: React.FC = (props: any) => {
	const postId = props.match.params.id

	// 文章
	const [post, setPost] = useState<Post>()
	// 评论
	const [comments, setComments] = useState<Array<Comment>>([])
	// 评论分页
	const [page, setPage] = useState<number>(1)
	// 评论总页数
	const [pages, setPages] = useState<number>(0)
	// 回复评论
	const [replyComment, setReplyComment] = useState<Comment | null>(null)
	// 昵称
	const [author, setAuthor] = useState<string>('')
	// 邮件
	const [email, setEmail] = useState<string>('')
	// 站点
	const [site, setSite] = useState<string>('')
	// 内容
	const [body, setBody] = useState<string>('')

	// 文章数据
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

	// 评论分页数据
	useEffect(() => {
		const fetchData = async () => {
			const comments = await getComments(postId, page, 5)
			if (comments?.success && comments.code === 1) {
				setComments(comments.data.records)
				setPages(comments.data.pages)
			}
		}
		fetchData()
	}, [postId, page])

	const handleSubmit = () => {
		if (body === null || body.replace(/(^\s*)|(\s*$)/g, '') === '') {
			alert('发表内容不能为空！')
			return
		}
		if (author === null || author.replace(/(^\s*)|(\s*$)/g, '') === '') {
			alert('昵称不能为空！')
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
					const comments = await getComments(postId, page, 5)
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

	// @ts-ignore
	return (
		<div
			className='md:max-w-screen-lg w-full md:my-8 md:mx-auto bg-white '
			style={{
				boxShadow: '0 0 4px 3px rgb(0 0 0 / 5%)',
				backgroundColor: '#f9f9f9',
			}}
		>
			<Header />
			<div className='md:px-8 p-2 md:py-6'>
				<div className='flex flex-col mb-4'>
					<span className='text-2xl mb-2 font-semibold'>{post?.title}</span>
					<span className='text-xs mb-2'>
						分类：{post?.category?.name} 日期：
						{dayjs(post?.timestamp).format('YYYY-MM-DD HH:mm:ss')} 点击数：
						{post?.pageView}
					</span>
					<div className='p-4'>
						<span className='text-sm'>{post?.subtitle}</span>
					</div>
				</div>
				<article className='leading-relaxed' dangerouslySetInnerHTML={{ __html: post?.body || '' }} />
				<div className='flex justify-between py-8 text-sm'>
					<span style={{ borderBottom: '1px dashed #e5e5e5' }}>
						{post?.nextPostId === null ? (
							''
						) : (
							<LinkTo to={'/post/' + post?.nextPostId}>
								← {post?.nextPostTitle}
							</LinkTo>
						)}
					</span>
					<span style={{ borderBottom: '1px dashed #e5e5e5' }}>
						{post?.prevPostId === null ? (
							''
						) : (
							<LinkTo to={'/post/' + post?.prevPostId}>
								{post?.prevPostTitle} →
							</LinkTo>
						)}
					</span>
				</div>
				{post?.canComment ? (
					<div>
						<hr className='my-4' />
						<div>
							<span className='text-xl font-semibold'>评论:</span>
							<div className='py-4'>
								{/* 评论列表 */}
								{comments.length === 0 ? (
									<span>暂无评论，尝试发表你的意见？</span>
								) : (
									comments.map((comment: Comment) => {
										return comment.repliedId === null ? (
											<div
												className='flex flex-col py-2'
												style={{ borderBottom: '1px dashed #e5e5e5' }}
												key={comment.id}
											>
												<span>{comment.author} 说:</span>
												<span className='p-3'>{comment.body}</span>
												<div
													className='text-sm text-gary-500'
													style={{ alignSelf: 'flex-end' }}
												>
													{dayjs(comment.timestamp).format(
														'YYYY-MM-DD HH:mm:ss'
													)}{' '}
													|&nbsp;
													<button
														onClick={() => {
															setReplyComment(comment)
														}}
													>
														回复
													</button>
												</div>
											</div>
										) : (
											<div
												className='flex flex-col py-2'
												style={{ borderBottom: '1px dashed #e5e5e5' }}
												key={comment.id}
											>
												<span>{comment.author} 说:</span>
												<span className='pt-3 pl-6'>
													@{comment.comment?.author} 说: {comment.comment?.body}
												</span>
												<span className='p-3'>{comment.body}</span>
												<div
													className='text-sm text-gary-500'
													style={{ alignSelf: 'flex-end' }}
												>
													{dayjs(comment.timestamp).format(
														'YYYY-MM-DD HH:mm:ss'
													)}{' '}
													|&nbsp;
													<button
														onClick={() => {
															setReplyComment(comment)
														}}
													>
														回复
													</button>
												</div>
											</div>
										)
									})
								)}
							</div>
							{/* 分页 */}
							{pages <= 1 ? (
								''
							) : (
								<div className='flex justify-between pt-4'>
									<button
										className='p-2 font-medium disabled:opacity-50'
										onClick={() => setPage(page - 1)}
										disabled={page <= 1}
									>
										←Prev
									</button>
									<button
										className='p-2 font-medium disabled:opacity-50'
										onClick={() => setPage(page + 1)}
										disabled={page >= pages}
									>
										Next→
									</button>
								</div>
							)}
						</div>
						<hr className='my-4' />
						<div>
							<div className='flex flex-col bg-gray-50'>
								<span className='mb-4 text-lg'>
									{replyComment === null
										? '发表评论'
										: '回复 ' +
										  replyComment?.author +
										  ': ' +
										  replyComment?.body}
								</span>
								<div className='mb-4'>
									<label className='pr-2 text-sm'>评论:</label>
									<textarea
										name='body'
										id='body'
										className='md:w-56 sm:w-50 w-60 h-16'
										value={body}
										onChange={(event) => setBody(event.target.value)}
									/>
								</div>
								<div className='flex flex-col md:flex-row md:justify-between'>
									<div className='mb-4 md:w-1/3'>
										<label className='pr-2 text-sm'>昵称:</label>
										<input
											type='text'
											className='md:w-56 sm:w-50 w-60'
											value={author}
											onChange={(event) => setAuthor(event.target.value)}
										/>
									</div>
									<div className='mb-4 md:w-1/3'>
										<label className='pr-2 text-sm'>邮箱:</label>
										<input
											type='text'
											className='md:w-56 sm:w-50 w-60'
											value={email}
											onChange={(event) => setEmail(event.target.value)}
										/>
									</div>
									<div className='mb-4 md:w-1/3'>
										<label className='pr-2 text-sm'>站点:</label>
										<input
											type='text'
											className='md:w-56 sm:w-50 w-60'
											value={site}
											onChange={(event) => setSite(event.target.value)}
										/>
									</div>
								</div>
								<div>
									<button
										type='submit'
										className='bg-gray-800 text-white rounded py-1 px-2 hover:bg-gray-900'
										onClick={handleSubmit}
									>
										提 交
									</button>
								</div>
							</div>
						</div>
					</div>
				) : (
					''
				)}
			</div>
			<Footer />
		</div>
	)
}

export default PostPage
