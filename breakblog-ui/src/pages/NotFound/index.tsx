import React from 'react'

const NotFound: React.FC = () => {
	return (
		<div className='md:px-8 p-2 md:py-6'>
			<div className='mb-4 p-2 md:p-4 bg-white dark:bg-gray-700 rounded'>
				<span className='text-xl md:text-2xl dark:text-gray-400'>
					404 - Not Found
				</span>
			</div>
		</div>
	)
}

export default NotFound
