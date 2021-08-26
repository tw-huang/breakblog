import React from 'react'
import './index.css'
import Header from '../../compents/Header'
import Footer from '../../compents/Footer'

const Post: React.FC = (props: any) => {
	const postId = props.match.params.id
	console.log(postId)
	// @ts-ignore
	return (
		<div className='md:max-w-screen-lg w-full md:my-8 md:mx-auto bg-white '>
			<Header />
			<div className='md:px-8 p-2 md:py-6'>
				<div className='flex justify-between text-xs mb-4'>
					<span style={{ borderBottom: '1px dashed #e5e5e5' }}>
						首页 / 文章
					</span>
					<span style={{ borderBottom: '1px dashed #e5e5e5' }}>
						<a href='http://www.baidu.com'>返回首页</a>
					</span>
				</div>
				<div className='flex flex-col mb-4'>
					<span className='text-3xl mb-2'>Java性能测试世界</span>
					<span className='text-sm mb-2'>
						-- 分类：Java 日期：2015-08-12 点击数：1120
					</span>
					<div className='p-4 bg-gray-50'>
						<span>
							2020年来，这是新年的第一篇文章，有时候真的很懒，博客一直没能按时更新，可能也觉得没啥人看，没动力写下去。回头想想，自己也不去交换友链，不推广博客，这也是我的问题。今晚有心情，就写写近况吧~
						</span>
					</div>
				</div>
				<div className='leading-relaxed'>
					今年应该是我记忆中，新年节日气氛最没氛围的，最主要的还是新冠病毒的原因，我这情况是这样的，农村里都没人出门，探亲、聚会都没了。
					我呢，带了一台女朋友不用的dell旧笔记本和一本spring实战回家看书学习，回到家里，书也就看了一两章，笔记本也没打开过几次。过年前还帮女朋友升级了电脑，加了内存，加了固态，想着过年回家好好学习，结果，呵呵~~
					还好，本来想着年后三四月份出去找份java后端工作，但是最近发生这么严重的疫情，恐怕也要推迟了，情况看看再说吧！这两三个月来，一直有学习java，从java基础se，到servlet，spring，也有刷牛客上面的java基础题目，现在大概刷了一半题库，准确率只有6成左右，好好加油吧。
					项目的话，用servlet+jsp重构了现在用的flask写的博客，我知道这些技术比较落后了，外面的公司都不用了，但是servlet肯定必须写好，毕竟springmvc底层也是servlet嘛。
					看书和看b站上面的黑马视频，学spring、springmvc和mybatis相关技术。所以最近用spring+springmvc+mybatis
					(ssm)+jsp
					又重构了现在的breakblog博客，项目也基本写完了。迟点把最近的写的两个项目放github上面。
					下一步干嘛？当然学springboot啦，springmvc毕竟也算了解了皮毛，学springboot也不妨碍深入理解spring吧，jsp是不想再用了，今晚去优化了一下现在的博客，发现还是flask的jinjia2模板爽啊，哈哈哈哈哈。springboot推荐的thymeleaf模板引擎也得学学，下个项目打算用springboot
					加thymeleaf模板引擎再写一遍这个博客吧，然后写完之后，就是前后端分离的事情了。大前端的浪潮下，vue怎么也得学学吧，幸好学java之前有接触过，难不倒我的，springboot+vue前后端分离搞起。搞着这些，换个高难度的项目试试，从头搞起，肯定学到不少的。
					最后，还是好好把java基础学好，多线程，jvm虚拟机，底层这些要了解呀，精通不要求，基本怎么也得会吧。好吧，半夜四点多了。该睡觉了~再见！
				</div>
				<div className='flex justify-between py-8 text-sm'>
					<span style={{ borderBottom: '1px dashed #e5e5e5' }}>
						<a href='http://www.baidu.com'>上一篇 那些与美食有关的事儿</a>
					</span>
					<span style={{ borderBottom: '1px dashed #e5e5e5' }}>
						<a href='http://www.baidu.com'>下一篇 化妆品到底有没有用</a>
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
							<textarea name='body' id='body' className='md:w-56 sm:w-50 w-60 h-16' />
						</div>
						<div className='flex flex-col md:flex-row md:justify-between'>
							<div className='mb-4 md:w-1/3'>
								<label className='pr-2 text-sm'>昵称:</label>
								<input type='text' className='md:w-56 sm:w-50 w-60'/>
							</div>
							<div className='mb-4 md:w-1/3'>
								<label className='pr-2 text-sm'>邮箱:</label>
								<input type='text' className='md:w-56 sm:w-50 w-60'/>
							</div>
							<div className='mb-4 md:w-1/3'>
								<label className='pr-2 text-sm'>站点:</label>
								<input type='text' className='md:w-56 sm:w-50 w-60'/>
							</div>
						</div>
						<div>
							<button className='bg-gray-800 text-white rounded py-1 px-2 hover:bg-gray-900'>提 交</button>
						</div>
					</div>
				</div>
			</div>
			<Footer />
		</div>
	)
}

export default Post
