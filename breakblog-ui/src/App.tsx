import React from 'react'
// @ts-ignore
import { Route, Switch } from 'react-router-dom'
import Home from './pages/Home'
import Post from './pages/Post'

const App: React.FC = () => {
	return (
		<Switch>
			<Route exact path='/' component={Home} />
			<Route exact path='/post/:id' component={Post} />
		</Switch>
	)
}

export default App
