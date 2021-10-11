import React from 'react'

interface Props {
	pages: number
	page: number
	setPage: any
}

const Pagination: React.FC<Props> = (props) => {
	const { pages, page, setPage } = props
	return (
		<>
			{pages <= 1 ? (
				''
			) : (
				<div className='flex justify-between items-center mt-4 md:mt-6 font-medium bg-white dark:bg-gray-700 dark:text-gray-400 hover:text-gray-600 rounded'>
					<button
						className={`p-2  focus:outline-none ${
							page <= 1 ? 'disabled:opacity-50' : 'hover:underline'
						}`}
						onClick={() => setPage(page - 1)}
						disabled={page <= 1}
					>
						←Prev
					</button>
					<span className='text-gray-400 text-sm'>
						{page} / {pages}
					</span>
					<button
						className={`p-2 focus:outline-none ${
							page >= pages ? 'disabled:opacity-50' : 'hover:underline'
						}`}
						onClick={() => setPage(page + 1)}
						disabled={page >= pages}
					>
						Next→
					</button>
				</div>
			)}
		</>
	)
}

export default Pagination
