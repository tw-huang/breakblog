import React, { useContext, useEffect, useState } from 'react'
// @ts-ignore
import { Link as LinkTo } from 'react-router-dom'
import AppContext from '../../store'
import Logo from '../../assets/logo.png'

interface BlogInfo {
	name: string
	avatar: string
	email: string
	blogTitle: string
	blogSubTitle: string
}

const Header: React.FC = () => {
	/** 上下文 */
	// @ts-ignore
	const context = useContext<BlogInfo>(AppContext)
	/** 夜间模式 */
	const [nightMode, setNightMode] = useState<boolean>(false)

	/** 自适应系统夜间模式 或 用户是否开启夜间模式 */
	useEffect(() => {
		if (
			localStorage.theme === 'dark' ||
			(!('theme' in localStorage) &&
				window.matchMedia('(prefers-color-scheme: dark)').matches)
		) {
			// @ts-ignore
			document.querySelector('html').classList.add('dark')
			setNightMode(true)
		} else {
			// @ts-ignore
			document.querySelector('html').classList.remove('dark')
			setNightMode(false)
		}
	}, [])

	const handleClick = () => {
		if (!nightMode) {
			// @ts-ignore
			document.querySelector('html').classList.add('dark')
		} else {
			// @ts-ignore
			document.querySelector('html').classList.remove('dark')
		}
		setNightMode(!nightMode)
	}

	return (
		<div className='flex justify-between items-center md:h-20 h-12 md:px-8 px-2 border-solid border-b border-gray-200 dark:border-gray-900'>
			<LinkTo to={'/'} className='flex items-center'>
				<img src={Logo} alt='logo' className='md:w-10 w-8' />
				<span className='font-bold text-2xl pl-2 dark:text-gray-400'>
					{context.blogTitle}
				</span>
			</LinkTo>
			<div className='flex items-center font-medium'>
				<LinkTo
					to={'/'}
					className='hover:text-gray-600 dark:text-gray-400 hover:underline'
				>
					首页
				</LinkTo>
				<LinkTo
					to={'/archive'}
					className='ml-4 hover:text-gray-600 dark:text-gray-400 hover:underline'
				>
					归档
				</LinkTo>
				<LinkTo
					to={'/about'}
					className='ml-4 hover:text-gray-600 dark:text-gray-400 hover:underline'
				>
					关于
				</LinkTo>
				<div className='ml-4 cursor-pointer' onClick={handleClick}>
					<div
						hidden={nightMode}
						className='transition duration-500 ease-in-out transform hover:scale-110'
					>
						<svg
							d='1631717532983'
							className='icon'
							viewBox='0 0 1024 1024'
							version='1.1'
							xmlns='http://www.w3.org/2000/svg'
							p-id='5321'
							width='24'
							height='24'
						>
							<path
								d='M513.173333 128A255.061333 255.061333 0 0 0 448 298.666667c0 141.376 114.624 256 256 256a255.36 255.36 0 0 0 189.802667-84.202667c1.450667 13.653333 2.197333 27.498667 2.197333 41.536 0 212.074667-171.925333 384-384 384S128 724.074667 128 512c0-209.706667 168.106667-380.16 376.96-383.936L513.152 128z m-117.824 85.930667l-3.52 1.408C274.645333 262.826667 192 377.770667 192 512c0 176.725333 143.274667 320 320 320 145.408 0 268.16-96.981333 307.114667-229.802667l1.536-5.504-1.6 0.64a319.509333 319.509333 0 0 1-106.496 21.226667L704 618.666667c-176.725333 0-320-143.274667-320-320 0-28.48 3.754667-56.405333 10.944-83.2l0.405333-1.536z'
								fill='#4B5563'
								p-id='5322'
							/>
						</svg>
					</div>
					<div
						hidden={!nightMode}
						className='transition duration-500 ease-in-out transform hover:scale-110'
					>
						<svg
							d='1631718043424'
							className='icon'
							viewBox='0 0 1024 1024'
							version='1.1'
							xmlns='http://www.w3.org/2000/svg'
							p-id='6579'
							width='24'
							height='24'
						>
							<path
								d='M512 232c-154.6 0-280 125.4-280 280s125.4 280 280 280 280-125.4 280-280-125.4-280-280-280z m162.6 442.6c-21.1 21.1-45.7 37.7-73.1 49.3-28.3 12-58.4 18.1-89.5 18.1s-61.2-6.1-89.5-18.1c-27.4-11.6-52-28.2-73.1-49.3-21.1-21.1-37.7-45.7-49.3-73.1-12-28.3-18.1-58.4-18.1-89.5s6.1-61.2 18.1-89.5c11.6-27.4 28.2-52 49.3-73.1 21.1-21.1 45.7-37.7 73.1-49.3 28.3-12 58.4-18.1 89.5-18.1s61.2 6.1 89.5 18.1c27.4 11.6 52 28.2 73.1 49.3 21.1 21.1 37.7 45.7 49.3 73.1 12 28.3 18.1 58.4 18.1 89.5s-6.1 61.2-18.1 89.5c-11.5 27.4-28.1 52-49.3 73.1zM537 165.1h-50v-100h50v100z m-50 693.8h50v100h-50v-100zM249 284.4l-70.7-70.7 35.4-35.4 70.7 70.7-35.4 35.4z m526 455.2l70.7 70.7-35.4 35.4-70.7-70.7 35.4-35.4zM65.1 487h100v50h-100v-50z m893.8 0v50h-100v-50h100zM249 739.6l35.4 35.4-70.7 70.7-35.4-35.4 70.7-70.7z m561.3-561.3l35.4 35.4-70.7 70.7-35.4-35.4 70.7-70.7z'
								fill='#9CA3AF'
								p-id='6580'
							/>
						</svg>
					</div>
				</div>
			</div>
		</div>
	)
}

export default Header
