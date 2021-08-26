import React from 'react'
import AwesomeSlider from 'react-awesome-slider'
// @ts-ignore
import withAutoplay from 'react-awesome-slider/dist/autoplay';
import 'react-awesome-slider/dist/styles.css'
import './index.css'
// @ts-ignore
import Banner1Img from '../../assets/banner1.jpg'
import Banner2Img from '../../assets/banner2.jpg'
import Banner3Img from '../../assets/banner3.jpg'

const AutoplaySlider = withAutoplay(AwesomeSlider);

const Banner: React.FC = () => {
	return (
		<div>
			<AutoplaySlider
				bullets={false}
				organicArrows={false}
				play={true}
				cancelOnInteraction={false}
				interval={10000}
				className='h-40 md:h-56 w-full'
				media={[
					{
						source: Banner1Img,
					},
					{
						source: Banner2Img,
					},
					{
						source: Banner3Img,
					},
				]}
			/>
			{/*<img src={Banner1Img} alt="logo"/>*/}
		</div>
	)
}

export default Banner