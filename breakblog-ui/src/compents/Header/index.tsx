import React, { useEffect, useState } from 'react'
// @ts-ignore
import { Link as LinkTo } from 'react-router-dom'
import Logo from '../../assets/logo.png'
import './index.css'
interface Props {
	blogTitle: string
}

const Header: React.FC<Props> = (props) => {
	const blogTitle = props.blogTitle

	const [nightMode, setNightMode] = useState<boolean>(false)

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
					{blogTitle}
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
				<div
					className='ml-4 rounded-full h-4 w-4  flex items-center justify-center cursor-pointer shadow-inner'
					onClick={handleClick}
				>
					<span className='h-4 w-2 rounded-l-lg bg-white dark:bg-gray-900' />
					<span className='h-4 w-2 rounded-r-lg bg-gray-200 dark:bg-gray-600' />
				</div>
			</div>
		</div>
	)
}

export default Header
