import React, { Component } from "react";
import {
  Platform,  
  StyleSheet,  
  Text,  
  View,  
  Button,  
  Image, 
  StatusBar,   
  Dimensions,
  TextInput,
  TouchableHighlight,
  PanResponder,
  ToastAndroid,
  PixelRatio,
  Animated} from "react-native";

  import Modal,{
    ModalTitle,
    ModalContent,
    ModalFooter,
    ModalButton,
    SlideAnimation,
  } from "react-native-modals"

export default class GestureStudy extends Component{

  state = {
    marginLeft:55,
    marginTop:55,
    lastMarginLeft:55,
    lastMarginTop:55,
    // 验证弹框是否显示
    isShowDialogVisible:false
  }

  componentWillMount(){
    this._panResponder = PanResponder.create({
      onStartShouldSetPanResponder:(evt,gs) => true,
      onMoveShouldSetPanResponder:  (evt, gs) => true,
      onStartShouldSetPanResponderCapture: (evt, gestureState) => false,
      onMoveShouldSetPanResponderCapture: (evt, gs) => true,
      onPanResponderGrant: (evt, gs) => {
        console.log(`onPanResponderGrant call: gs=` ,gs);

        this.setState({
          marginLeft:this.state.lastMarginLeft + gs.dx,
          marginTop:this.state.lastMarginTop + gs.dy
        })
      },
      onResponderReject: (e) => {
        console.log('onResponderReject==>' + '响应者申请失败')
      },
      onPanResponderMove: (evt, gs) => {
        console.log(`onPanResponderMove call: gs=` ,gs);
        console.log(`gs.X : ${gs.dx}   Y : ${gs.dy}`);

        this.setState({
          marginLeft:this.state.lastMarginLeft + gs.dx,
          marginTop:this.state.lastMarginTop + gs.dy
        })
      },
      onPanResponderEnd: (evt, gs) => {
        console.log(`onPanResponderEnd call: gs=` ,gs);
        this.setState({
          lastMarginLeft: this.state.marginLeft,
          lastMarginTop: this.state.marginTop
        })
      },
      onPanResponderRelease: (evt, gs) => {
        console.log(`onPanResponderRelease call: gs=` ,gs);
      },
      onPanResponderTerminate: (evt, gs) => {
        console.log(`onPanResponderTerminate call: gs=` ,gs);
      },
      // 其他的东西想成为响应器。这种视图应该释放应答吗？返回 true 就是允许释放 
      onPanResponderTerminationRequest:(evt, gs) => {
        console.log(`onPanResponderTerminationRequest call: gs=` ,gs);
        // 如果返回true,Modal里面的手势不能正常使用
        return false;
      },
      onShouldBlockNativeResponder: (evt, gs) => {
        console.log(`onShouldBlockNativeResponder call: gs=` ,gs);
        return true;
      },
    })
  }

  render(){
    return(
      <View style={{backgroundColor:"#ffffff",width:Dimensions.get("screen").width,height:Dimensions.get("screen").height}}>
          {/* <View 
            style={{backgroundColor:"#66bb6a",width:50,height:50,borderRadius:25,marginLeft:this.state.marginLeft,marginTop:this.state.marginTop}}
            {...this._panResponder.panHandlers}/> */}

      <Text style={{textAlign:"center",
        textAlignVertical:"center", 
        backgroundColor:"#f35656", 
        width:97,height:30,marginLeft:this.state.marginLeft,marginTop:this.state.marginTop, marginRight:16,
        color:"#ffffff",
        borderRadius:15}}
        onPress={()=>{
          this.setState({
            isShowDialogVisible:true
          })
        }}
        // {...this._panResponder.panHandlers}
        >拖动滑块</Text>
        {/* 验证弹框 */}
        {this.verificationDialog()}
      </View>
    );
  }

  // dialog弹框提示
  verificationDialog(){
    return(
      <View>
        <Modal 
          width={0.8}    
          height={0.3}      
          rounded={false}
          actionsBordered
          visible={this.state.isShowDialogVisible}
          onTouchOutside={()=>{this.setState({isShowDialogVisible:false})}}>
          <View 
            style={{
              backgroundColor:"#66bb6a",
              width:50,height:50,
              borderRadius:25,
              marginLeft:this.state.marginLeft,marginTop:this.state.marginTop}}
            {...this._panResponder.panHandlers}/>
        </Modal>
      </View>
    );
  }
}