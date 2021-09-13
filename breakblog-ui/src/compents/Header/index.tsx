import React from 'react'
// @ts-ignore
import { Link as LinkTo } from 'react-router-dom'
import Logo from '../../assets/logo.png'

const Header: React.FC = () => {
	return (
		<div className='flex justify-between items-center md:h-20 h-12 md:px-8 px-2 border-solid border-b-2 border-gray-200'>
			<LinkTo to={'/'} className='flex items-center'>
				<img src={Logo} alt='logo' className='md:w-12 w-8' />
				<span className='font-bold text-2xl pl-2'>随笔记录</span>
			</LinkTo>
			<div className='font-medium'>
				<LinkTo to={'/'} className='hover:text-gray-400 hover:underline'>
					首页
				</LinkTo>
				<LinkTo
					to={'/archive'}
					className='ml-4 hover:text-gray-400 hover:underline'
				>
					归档
				</LinkTo>
				<span className='ml-4 hover:text-gray-400 hover:underline'>关于</span>
			</div>
		</div>
	)
}

export default Header
