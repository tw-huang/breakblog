import React, { useContext } from 'react'
import AppContext from '../../store'

interface BlogInfo {
	name: string
	avatar: string
	email: string
	blogTitle: string
	blogSubTitle: string
}

const Footer: React.FC = () => {
	/** 上下文 */
	// @ts-ignore
	const context = useContext<BlogInfo>(AppContext)

	return (
		<div className='flex justify-between items-center md:h-20 h-12 md:px-8 px-2 border-solid border-t border-gray-200 dark:border-gray-900'>
			<span className='text-sm hover:text-gray-400 text-gray-600 dark:text-gray-500 hover:underline'>
				<a
					href='https://github.com/tw-huang/breakblog'
					target='_blank'
					rel='noopener noreferrer'
				>
					Copyright © {context.name}
				</a>
			</span>
			<span className='text-sm hover:text-gray-400 text-gray-600 dark:text-gray-500 hover:underline'>
				<a
					href='https://beian.miit.gov.cn'
					target='_blank'
					rel='noopener noreferrer'
				>
					粤ICP备2021107751号
				</a>
			</span>
		</div>
	)
}

export default Footer
