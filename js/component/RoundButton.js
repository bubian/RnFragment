import React, { Component } from 'react';
import {
  TouchableOpacity,
  TouchableHighlight,
  View,
  Text,
  Dimensions,
  StyleSheet,
} from 'react-native'

export default class RoundButton extends Component{
  render(){
    return(
      <TouchableHighlight style={{marginTop:10}}>
          <View style={styles.btn}>
            <Text style={{fontSize:17,color:"#ffffff"}}>开启医联</Text>
          </View>
      </TouchableHighlight>
    );
  }
}
const styles = StyleSheet.create({
  btn:{
    marginTop:30,
    height:45,
    backgroundColor:'#007eff',
    justifyContent:'center',
    alignItems:'center',
    borderRadius:50,
  }
});