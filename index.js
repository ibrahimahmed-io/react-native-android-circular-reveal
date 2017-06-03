"use strict";

import React, {
    Component,
    PropTypes
} from 'react';

import {
    requireNativeComponent,
    View,
    UIManager,
    findNodeHandle
} from 'react-native';

export default class AndroidCircularReveal extends Component {
    props: {
        animationDuration ? : number
    };

    static propTypes = {
        ...View.propTypes,
        animationDuration: PropTypes.number
    };

    reveal = (positionFromRight) => {
        UIManager.dispatchViewManagerCommand(
            findNodeHandle(this),
            UIManager.ReactNativeAndroidCircularReveal.Commands.REVEAL,
            [positionFromRight]
        );
    };

    hide = (positionFromRight) => {
        UIManager.dispatchViewManagerCommand(
            findNodeHandle(this),
            UIManager.ReactNativeAndroidCircularReveal.Commands.HIDE,
            [positionFromRight]
        );
    };

    render() {
        return (
            <RCTCircularRevealLayout
		        {...this.props}
		        style={this.props.style}>
		        {this.props.children}
		    </RCTCircularRevealLayout>
        );
    }
}

let RCTCircularRevealLayout = requireNativeComponent('ReactNativeAndroidCircularReveal', AndroidCircularReveal, {
    nativeOnly: {}
});

module.exports = AndroidCircularReveal;
