import React from 'react'

interface Props {
	name: string
}

const Footer: React.FC<Props> = (props) => {
	const name = props.name

	return (
		<div className='flex justify-between items-center md:h-20 h-12 md:px-8 px-2 border-solid border-t-2 border-gray-200'>
			<span className='text-sm hover:text-gray-400 hover:underline'>
				<a
					href='https://github.com/tw-huang/breakblog'
					target='_blank'
					rel='noopener noreferrer'
				>
					Copyright © {name}
				</a>
			</span>
			<span className='text-sm hover:text-gray-400 hover:underline'>
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
