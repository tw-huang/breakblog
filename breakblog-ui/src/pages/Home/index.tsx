import React from 'react'
import './index.css'
import Header from '../../compents/Header'
import Footer from '../../compents/Footer'
import Banner from '../../assets/banner.jpg'
import PostImg from '../../assets/post.jpg'

const Home: React.FC = () => {
	return (
		<div className='md:max-w-screen-lg w-full h-screen md:my-8 md:mx-auto relative bg-white '>
			<Header />
			<div className='flex flex-col md:flex-row'>
				<div className='md:px-8 px-2 md:w-3/4 md:py-6'>
					<div>
						<img src={Banner} alt='banner' className='w-full' />
					</div>
					<div className='md:pt-4'>
						<div className='flex'>
							<div className="mr-2 md:w-1/3 hidden md:block">
								<img src={PostImg} alt='jpg'/>
							</div>
							<div className='flex flex-col md:w-2/3'>
								<a href='http://www.baidu.com'>深巷魅惑</a>
								<span>分类：Java 日期：2015-08-12 点击数：1120</span>
								<span>
									风一程、雨一程，为那舌尖美食；却意外收尽旖旎、迷人；车几刻、行几刻；埔港深巷如烟迷蒙；是戴望舒的“雨巷”？“撑着油纸伞，独自彷徨在悠长悠长，又寂寥的雨巷，我希望逢着一个”……车如流水，人如织；喧哗让我莫遐思……
								</span>
								<a href='http://www.baidu.com'>阅读正文</a>
							</div>
						</div>
					</div>
					<div>page</div>
				</div>
				<div className='md:pr-8 md:pl-0 px-2 md:w-1/4 md:py-6'>sider</div>
			</div>
			<Footer />
		</div>
	)
}

export default Home
