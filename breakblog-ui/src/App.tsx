import React from 'react'
// @ts-ignore
import { Route, Switch } from 'react-router-dom'
import Home from './pages/Home'

const App: React.FC = () => {
	return (
		<Switch>
			<Route exact path='/' component={Home} />
		</Switch>
	)
}

export default App
