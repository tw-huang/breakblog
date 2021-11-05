import axios from 'axios'

let env = process.env.NODE_ENV
let baseUrl = ''

if (env === 'development') {
	baseUrl = 'http://localhost:9000/api'
} else if (env === 'production') {
	baseUrl = 'https://twhuang.top/api/breakblog'
}

// 获取文章列表
export async function getPosts(
	keyword: string,
	categoryId: number | null,
	pageNum: number,
	pageSize: number
) {
	return axios({
		url: baseUrl + '/blog/posts',
		method: 'get',
		params: {
			keyword: keyword,
			categoryId: categoryId,
			pageNum: pageNum,
			pageSize: pageSize,
		},
	})
		.then((res) => {
			return res.data
		})
		.catch((err) => {
			console.log(err)
		})
}

// 获取文章评论列表
export async function getComments(
	postId: number,
	pageNum: number,
	pageSize: number
) {
	return axios({
		url: baseUrl + '/blog/comments',
		method: 'get',
		params: {
			postId: postId,
			pageNum: pageNum,
			pageSize: pageSize,
		},
	})
		.then((res) => {
			return res.data
		})
		.catch((err) => {
			console.log(err)
		})
}

// 提交文章评论
export async function postComment(
	postId: number,
	author: string,
	email: string,
	site: string,
	body: string,
	repliedId: any
) {
	return axios({
		url: baseUrl + '/blog/comment',
		method: 'post',
		headers: {
			'Content-type': 'application/json',
		},
		data: {
			postId: postId,
			author: author,
			email: email,
			site: site,
			body: body,
			repliedId: repliedId,
		},
	})
		.then((res) => {
			return res.data
		})
		.catch((err) => {
			console.log(err)
		})
}

// 获取归档文章列表
export async function getPostsArchive(pageNum: number, pageSize: number) {
	return axios({
		url: baseUrl + '/blog/posts/archive',
		method: 'get',
		params: {
			pageNum: pageNum,
			pageSize: pageSize,
		},
	})
		.then((res) => {
			return res.data
		})
		.catch((err) => {
			console.log(err)
		})
}

// 获取关于
export async function getBlogAbout() {
	return axios({
		url: baseUrl + '/blog/about',
		method: 'get',
	})
		.then((res) => {
			return res.data
		})
		.catch((err) => {
			console.log(err)
		})
}

// 获取文章
export async function getPost(postId: number) {
	return axios({
		url: baseUrl + '/blog/post/' + postId,
		method: 'get',
	})
		.then((res) => {
			return res.data
		})
		.catch((err) => {
			console.log(err)
		})
}

// 获取热门文章
export async function getPostsHot() {
	return axios({
		url: baseUrl + '/blog/posts/hot',
		method: 'get',
	})
		.then((res) => {
			return res.data
		})
		.catch((err) => {
			console.log(err)
		})
}

// 获取分类
export async function getCategories() {
	return axios({
		url: baseUrl + '/blog/categories',
		method: 'get',
	})
		.then((res) => {
			return res.data
		})
		.catch((err) => {
			console.log(err)
		})
}

// 获取友链
export async function getLinks() {
	return axios({
		url: baseUrl + '/blog/links',
		method: 'get',
	})
		.then((res) => {
			return res.data
		})
		.catch((err) => {
			console.log(err)
		})
}

// 获取博客信息
export async function getBlogInfo() {
	return axios({
		url: baseUrl + '/blog/info',
		method: 'get',
	})
		.then((res) => {
			return res.data
		})
		.catch((err) => {
			console.log(err)
		})
}

// 获取博客统计
export async function getBlogStatistic() {
	return axios({
		url: baseUrl + '/blog/statistic',
		method: 'get',
	})
		.then((res) => {
			return res.data
		})
		.catch((err) => {
			console.log(err)
		})
}
