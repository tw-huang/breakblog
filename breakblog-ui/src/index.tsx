import React from 'react'
import ReactDOM from 'react-dom'
// @ts-ignore
import { BrowserRouter } from 'react-router-dom'
import { AppProvider } from './store'
import App from './App'
import './index.css'

ReactDOM.render(
	<AppProvider>
		<BrowserRouter>
			<React.StrictMode>
				<App />
			</React.StrictMode>
		</BrowserRouter>
	</AppProvider>,
	document.getElementById('root')
)
