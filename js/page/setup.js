/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import { Image , Text , View ,StyleSheet} from 'react-native';
import InputTextView from '../component/InputTextView'
import SnackBar from '../component/SnackBarView'
class Greeting extends Component{
    constructor(props){
        super(props);
        this.state = {isShowText: true};
        setInterval(() => {
            this.setState(previousState => {
                return{isShowText: !previousState.isShowText};
                });
        }, 1000);
    }
    render(){
        if(!this.state.isShowText){
            return null;
        }
        return(
            <View style= {{alignItems: "center"}}>
                <Text style={styles.bigBlue}>I Love {this.props.name}</Text>
            </View>
        );
    }
}

export default class Bananas extends Component{
    render(){
        // let pic = {
        //     uri: 'https://upload.wikimedia.org/wikipedia/commons/d/de/Bananavarieties.jpg' 
        // };
        // return(
        //     <Image source = {pic} style={{width: 193,height: 110}}/>
        // );

        return(
            <View style={{alignItems: "center"}}>
                <InputTextView/>
                {/* <Greeting name="you"/>
                <Greeting name="you too"/> */}
            </View>
        );
    }
}

const styles = StyleSheet.create({
    bigBlue: {
        color: 'blue',
        fontWeight: 'bold',
        fontSize: 30,
        alignItems: 'center',
    },
    red: {
        color: 'red',
    },
});

