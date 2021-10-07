import React, { createContext, useEffect, useState } from 'react'
import { getBlogInfo } from './services'

const AppContext = createContext({})
const { Provider } = AppContext

interface BlogInfo {
	name: string
	avatar: string
	email: string
	blogTitle: string
	blogSubtitle: string
}

export function AppProvider(props: any) {
	/** 上下文  */
	const [blogInfo, setBlogInfo] = useState<BlogInfo>({
		blogSubtitle: 'You still have lots more to work on!',
		blogTitle: 'BreakBlog',
		email: 'tw.huang@foxmail.com',
		name: 'twhuang',
		avatar: 'https://twhuang.top/file/breakblog/avatar.png',
	})

	useEffect(() => {
		const fetchData = async () => {
			const blogInfo = await getBlogInfo()
			if (blogInfo?.success && blogInfo.code === 1) {
				setBlogInfo(blogInfo.data)
			}
		}
		fetchData()
	}, [])

	return <Provider value={blogInfo}> {props.children}</Provider>
}

export default AppContext
