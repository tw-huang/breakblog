import React, { useEffect, useState } from 'react'
// @ts-ignore
import { Route, Switch } from 'react-router-dom'
import Home from './pages/Home'
import About from './pages/About'
import Archive from './pages/Archive'
import Post from './pages/Post'
import Header from './compents/Header'
import Footer from './compents/Footer'
import { getBlogInfo } from './services'

interface BlogInfo {
	name: string
	avatar: string
	email: string
	blogTitle: string
	blogSubTitle: string
}

const App: React.FC = () => {
	const [blogInfo, setBlogInfo] = useState<BlogInfo>()

	/** 获取博客基本信息 */
	useEffect(() => {
		const fetchData = async () => {
			const blogInfo = await getBlogInfo()
			if (blogInfo?.success && blogInfo.code === 1) {
				setBlogInfo(blogInfo.data)
			}
		}
		fetchData()
	}, [])

	return (
		<div className='md:max-w-screen-lg w-full md:my-8 md:mx-auto bg-gray-100 dark:bg-gray-800 shadow rounded'>
			{/* 顶部 */}
			<Header blogTitle={blogInfo?.blogTitle || 'breakblog'} />
			{/* 内容 */}
			<Switch>
				<Route exact path='/' component={Home} />
				<Route exact path='/about' component={About} />
				<Route exact path='/archive' component={Archive} />
				<Route exact path='/post/:id' component={Post} />
			</Switch>
			{/* 尾部 */}
			<Footer name={blogInfo?.name || 'twhuang'} />
		</div>
	)
}

export default App
