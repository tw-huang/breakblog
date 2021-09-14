import React, { useEffect, useState } from 'react'
import './index.css'
import { getBlogAbout } from '../../services'

interface About {
	id: number
	createTime: string
	updateTime: string
	avatar: string
	username: string
	blogTitle: string
	blogSubTitle: string
	name: string
	email: string
	about: string
	phone: string
}

const PostPage: React.FC = () => {
	const [about, setAbout] = useState<About>()

	// 初始数据
	useEffect(() => {
		const fetchData = async () => {
			const posts = await getBlogAbout()
			if (posts?.success && posts.code === 1) {
				setAbout(posts.data)
			}
		}
		fetchData()
	}, [])

	return (
		<div className='md:px-8 p-2 md:py-6'>
			<div className='mb-4 p-2 md:p-4 bg-white rounded'>
				<article
					className='leading-relaxed  max-w-none prose-sm md:prose md:max-w-none'
					dangerouslySetInnerHTML={{ __html: about?.about || '' }}
				/>
			</div>
		</div>
	)
}

export default PostPage
