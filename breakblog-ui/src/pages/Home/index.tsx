import React from 'react'
import './index.css'
import Header from '../../compents/Header'
import Footer from '../../compents/Footer'

const Home: React.FC = () => {
	return (
		<div className='md:max-w-screen-lg w-full h-screen md:my-8 md:mx-auto relative bg-white '>
			<Header />
			<Footer />
		</div>
	)
}

export default Home
