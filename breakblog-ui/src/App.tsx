import React from 'react'
// @ts-ignore
import { Route, Switch } from 'react-router-dom'

import Home from './pages/Home'
import About from './pages/About'
import Archive from './pages/Archive'
import Post from './pages/Post'
import NotFound from './pages/NotFound'
import Header from './compents/Header'
import Footer from './compents/Footer'

const App: React.FC = () => {
	return (
		<div className='md:max-w-screen-lg w-full md:my-8 md:mx-auto bg-gray-100 dark:bg-gray-800 shadow rounded'>
			{/* 头部 */}
			<Header />
			{/* 内容 */}
			<Switch>
				<Route exact path='/' component={Home} />
				<Route exact path='/about' component={About} />
				<Route exact path='/archive' component={Archive} />
				<Route exact path='/post/:id' component={Post} />
				<Route exact path='*' component={NotFound} />
			</Switch>
			{/* 尾部 */}
			<Footer />
		</div>
	)
}

export default App
