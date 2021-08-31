import React, { useEffect, useState } from 'react'
// @ts-ignore
import { Link as LinkTo } from 'react-router-dom'
import './index.css'
import Header from '../../compents/Header'
import Footer from '../../compents/Footer'
import { fetchPost } from '../../services'
import dayjs from 'dayjs'

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
	prevPostId: number
	prevPostTitle: string
	nextPostId: number
	nextPostTitle: string
}

const PostPage: React.FC = (props: any) => {
	const postId = props.match.params.id
	console.log(postId)

	const [post, setPost] = useState<Post>()

	// 侧边栏数据
	useEffect(() => {
		const fetchData = async () => {
			const post = await fetchPost(postId)
			if (post?.success && post.code === 1) {
				setPost(post.data)
			}
		}
		//获取初始数据
		fetchData()
	}, [postId])

	// @ts-ignore
	return (
		<div className='md:max-w-screen-lg w-full md:my-8 md:mx-auto bg-white '>
			<Header />
			<div className='md:px-8 p-2 md:py-6'>
				<div className='flex justify-between text-xs mb-4'>
					<span>
						首页 / 文章
					</span>
					<span style={{ borderBottom: '1px dashed #e5e5e5' }}>
						<LinkTo to='/'>返回首页</LinkTo>
					</span>
				</div>
				<div className='flex flex-col mb-4'>
					<span className='text-3xl mb-2'>{post?.title}</span>
					<span className='text-sm mb-2'>
						分类：{post?.category?.name} 日期：
						{dayjs(post?.timestamp).format('YYYY-MM-DD HH:mm:ss')} 点击数：
						{post?.pageView}
					</span>
					<div className='p-4 bg-gray-50'>
						<span>{post?.subtitle}</span>
					</div>
				</div>
				<article>{post?.body}</article>
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
				<hr className='my-4' />
				<div>
					<span className='text-2xl'>评论:</span>
					<div className='py-4'>
						<div
							className='flex flex-col py-2'
							style={{ borderBottom: '1px dashed #e5e5e5' }}
						>
							<span>云墨雪 说:</span>
							<span className='p-3'>坐个沙发, ie10的普及率还有待观望啊..</span>
							<span
								className='text-sm text-gary-500'
								style={{ alignSelf: 'flex-end' }}
							>
								2015年7月15日 10:37 | 回复
							</span>
						</div>
						<div
							className='flex flex-col py-2'
							style={{ borderBottom: '1px dashed #e5e5e5' }}
						>
							<span>云墨雪 说:</span>
							<span className='p-3'>坐个沙发, ie10的普及率还有待观望啊..</span>
							<span
								className='text-sm text-gary-500'
								style={{ alignSelf: 'flex-end' }}
							>
								2015年7月15日 10:37 | 回复
							</span>
						</div>
						<div
							className='flex flex-col py-2'
							style={{ borderBottom: '1px dashed #e5e5e5' }}
						>
							<span>云墨雪 说:</span>
							<span className='pt-3 pl-6'>
								@云墨雪 说: 坐个沙发, ie10的普及率还有待观望啊..
							</span>
							<span className='p-3'>坐个沙发, ie10的普及率还有待观望啊..</span>
							<span
								className='text-sm text-gary-500'
								style={{ alignSelf: 'flex-end' }}
							>
								2015年7月15日 10:37 | 回复
							</span>
						</div>
					</div>
				</div>
				<hr className='my-4' />
				<div>
					<div className='flex flex-col bg-gray-50 p-6'>
						<span className='mb-4 text-lg'>回复 王永起：</span>
						<div className='mb-4'>
							<label className='pr-2 text-sm'>评论:</label>
							<textarea
								name='body'
								id='body'
								className='md:w-56 sm:w-50 w-60 h-16'
							/>
						</div>
						<div className='flex flex-col md:flex-row md:justify-between'>
							<div className='mb-4 md:w-1/3'>
								<label className='pr-2 text-sm'>昵称:</label>
								<input type='text' className='md:w-56 sm:w-50 w-60' />
							</div>
							<div className='mb-4 md:w-1/3'>
								<label className='pr-2 text-sm'>邮箱:</label>
								<input type='text' className='md:w-56 sm:w-50 w-60' />
							</div>
							<div className='mb-4 md:w-1/3'>
								<label className='pr-2 text-sm'>站点:</label>
								<input type='text' className='md:w-56 sm:w-50 w-60' />
							</div>
						</div>
						<div>
							<button className='bg-gray-800 text-white rounded py-1 px-2 hover:bg-gray-900'>
								提 交
							</button>
						</div>
					</div>
				</div>
			</div>
			<Footer />
		</div>
	)
}

export default PostPage
