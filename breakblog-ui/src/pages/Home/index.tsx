import React from 'react'
import './index.css'
import Header from '../../compents/Header'
import Footer from '../../compents/Footer'
import Banner from '../../assets/banner.jpg'
import PostImg from '../../assets/post.jpg'

const Home: React.FC = () => {
	return (
		<div className='md:max-w-screen-lg w-full md:my-8 md:mx-auto bg-white '>
			<Header />
			<div className='flex flex-col md:flex-row'>
				<div className='md:px-8 p-2 md:w-3/4 md:py-6'>
					<div>
						<img src={Banner} alt='banner' className='w-full' />
					</div>
					<div>
						<div className='flex md:pt-4 pt-2'>
							<div
								className='pr-2 hidden md:block '
								style={{ minWidth: '200px' }}
							>
								<img src={PostImg} alt='jpg' />
							</div>
							<div className='flex flex-col'>
								<a
									href='http://www.baidu.com'
									className='text-xl md:text-2xl pb-2'
								>
									Java性能测试世界
								</a>
								<span className='text-xs pb-2 text-gray-500'>
									分类：Java 日期：2015-08-12 点击数：1120
								</span>
								<span className='text-sm pb-2'>
									风一程、雨一程，为那舌尖美食；却意外收尽旖旎、迷人；车几刻、行几刻；埔港深巷如烟迷蒙；是戴望舒的“雨巷”？“撑着油纸伞，独自彷徨在悠长悠长，又寂寥的雨巷，我希望逢着一个”……车如流水，人如织；喧哗让我莫遐思……
								</span>
								<a
									href='http://www.baidu.com'
									className='text-sm text-gray-500'
									style={{ alignSelf: 'flex-end' }}
								>
									阅读正文-&gt;
								</a>
							</div>
						</div>
						<div className='flex md:pt-4 pt-2'>
							<div
								className='pr-2 hidden md:block '
								style={{ minWidth: '200px' }}
							>
								<img src={PostImg} alt='jpg' />
							</div>
							<div className='flex flex-col'>
								<a
									href='http://www.baidu.com'
									className='text-xl md:text-2xl pb-2'
								>
									Java性能测试世界
								</a>
								<span className='text-xs pb-2 text-gray-500'>
									分类：Java 日期：2015-08-12 点击数：1120
								</span>
								<span className='text-sm pb-2'>
									风一程、雨一程，为那舌尖美食；却意外收尽旖旎、迷人；车几刻、行几刻；埔港深巷如烟迷蒙；是戴望舒的“雨巷”？“撑着油纸伞，独自彷徨在悠长悠长，又寂寥的雨巷，我希望逢着一个”……车如流水，人如织；喧哗让我莫遐思……
								</span>
								<a
									href='http://www.baidu.com'
									className='text-sm text-gray-500'
									style={{ alignSelf: 'flex-end' }}
								>
									阅读正文-&gt;
								</a>
							</div>
						</div>
						<div className='flex md:pt-4 pt-2'>
							<div
								className='pr-2 hidden md:block '
								style={{ minWidth: '200px' }}
							>
								<img src={PostImg} alt='jpg' />
							</div>
							<div className='flex flex-col'>
								<a
									href='http://www.baidu.com'
									className='text-xl md:text-2xl pb-2'
								>
									Java性能测试世界
								</a>
								<span className='text-xs pb-2 text-gray-500'>
									分类：Java 日期：2015-08-12 点击数：1120
								</span>
								<span className='text-sm pb-2'>
									风一程、雨一程，为那舌尖美食；却意外收尽旖旎、迷人；车几刻、行几刻；埔港深巷如烟迷蒙；是戴望舒的“雨巷”？“撑着油纸伞，独自彷徨在悠长悠长，又寂寥的雨巷，我希望逢着一个”……车如流水，人如织；喧哗让我莫遐思……
								</span>
								<a
									href='http://www.baidu.com'
									className='text-sm text-gray-500'
									style={{ alignSelf: 'flex-end' }}
								>
									阅读正文-&gt;
								</a>
							</div>
						</div>
					</div>
					<div className='flex justify-between md:pt-8 pt-4 underline'>
						<a href='http://www.baidu.com'>←Prev</a>
						<a href='http://www.baidu.com'>Next→</a>
					</div>
				</div>
				<div className='md:pr-8 md:pl-0 px-2 md:w-1/4 md:py-6'>
					<div>
						<label>
							<input
								type='text'
								className='w-full border-2 border-back'
								placeholder='请输入搜索的关键词'
							/>
						</label>
					</div>
					<div className='md:mt-4 mt-2'>
						<span className='text-lg'>热门文章:</span>
						<div className='flex flex-col text-sm mt-1'>
							<div className='flex flex-col pb-1'>
								<a href='http://www.baidu.com' className='pb-1'>Java性能测试世界</a>
								<span className='text-xs text-gray-500'>
									日期：2015-08-12 点击数：1120
								</span>
							</div>
							<div className='flex flex-col pb-1'>
								<a href='http://www.baidu.com' className='pb-1'>Java性能测试世界</a>
								<span className='text-xs text-gray-500'>
									日期：2015-08-12 点击数：1120
								</span>
							</div>
							<div className='flex flex-col pb-1'>
								<a href='http://www.baidu.com' className='pb-1'>Java性能测试世界</a>
								<span className='text-xs text-gray-500'>
									日期：2015-08-12 点击数：1120
								</span>
							</div>
							<div className='flex flex-col pb-1'>
								<a href='http://www.baidu.com' className='pb-1'>Java性能测试世界</a>
								<span className='text-xs text-gray-500'>
									日期：2015-08-12 点击数：1120
								</span>
							</div>
						</div>
					</div>
					<div className='md:mt-4 mt-2'>
						<span className='text-lg'>文章分类:</span>
						<div className='flex flex-col text-sm mt-1'>
							<span className='pb-1'>
								<a href='http://www.baidu.com'>Java(6)</a>
							</span>
							<span className='pb-1'>
								<a href='http://www.baidu.com'>生活(7)</a>
							</span>
							<span className='pb-1'>
								<a href='http://www.baidu.com'>Linux(7)</a>
							</span>		<span className='pb-1'>
								<a href='http://www.baidu.com'>分享(7)</a>
							</span>

					</div>
					</div>
					<div className='md:mt-4 mt-2'>
						<span className='text-lg'>友情链接:</span>
						<div className='flex flex-col text-sm mt-1'>
							<span className='pb-1'>
								<a href='http://www.baidu.com'>Github</a>
							</span>
							<span className='pb-1'>
								<a href='http://www.baidu.com'>Gitee</a>
							</span>
							<span className='pb-1'>
								<a href='http://www.baidu.com'>导航狗</a>
							</span>
						</div>
					</div>
				</div>
			</div>
			<Footer />
		</div>
	)
}

export default Home
