import React from 'react'
import Logo from "../../assets/logo.png"

const Header: React.FC = () => {

	return (
		<div className="flex justify-between items-center md:h-20 h-12 md:px-8 px-2 border-solid border-b-2 border-gary-50">
				<div className="flex items-center">
					<img src={Logo} alt="logo" className="md:w-12 w-8"/>
					<span className="font-bold text-2xl pl-2">BreakBlog</span>
				</div>
				<div>
					<span>时间轴</span>
					<span className="ml-4">关于我</span>
				</div>
			</div>

	)
}

export default Header
