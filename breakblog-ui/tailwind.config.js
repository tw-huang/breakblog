// https://www.tailwindcss.cn/docs/configuration
module.exports = {
	purge: ['./src/**/*.{js,jsx,ts,tsx}', './public/index.html'],
	// or 'media' or 'class'
	darkMode: 'class',
	theme: {
		extend: {},
	},
	variants: {
		extend: {
			opacity: ['disabled'],
		},
	},
	plugins: [require('@tailwindcss/typography')],
}
