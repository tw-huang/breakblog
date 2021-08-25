import React from 'react'
import Logo from '../../assets/logo.png'

const Footer: React.FC = () => {

	return (
		<div className="flex justify-between items-center absolute bottom-0 left-0 md:h-20 h-12 md:px-8 px-2 w-full border-solid border-t-2 border-gary-50">
			<div className="">
				<span className="text-sm">Copyright © tw-huang</span>
			</div>
			<div>
				<a href="https://beian.miit.gov.cn" className="text-sm">粤ICP备2021107751号</a>
			</div>
		</div>
	)
}

export default Footer
