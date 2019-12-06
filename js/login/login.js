import React, { Component } from "react";
import RoundButton from "../component/RoundButton"
import Modal,{
  ModalTitle,
  ModalContent,
  ModalFooter,
  ModalButton,
  SlideAnimation,
} from "react-native-modals"
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
  TouchableOpacity,
  Animated} from "react-native";


WINDOW_WIDTH = Dimensions.get("screen").width;
export default class Login extends Component{

  state = {
    defaultAnimationModal: false,
    marginLeft:20,
    lastMarginLeft:20,
    isShowVerificationTv:true
  };
  render(){
    return (
      <View style={{flexDirection: "column",marginStart: 16,marginTop: 44,marginEnd: 16}}>
        <StatusBar backgroundColor="#ffffff" barStyle="dark-content"/>
        <View style={{flexDirection:"row", justifyContent: "space-between"}}>
          <Image source={require("./img/login_logo.png")}/>
          <Image source={require("../common/img/close_gray_24.png")} style={{padding: 4}}/>
        </View>
        <Text style={loginStyles.topTitle}>医者.世界  因你不同</Text>
        <TextInput 
          style={[loginStyles.input,{marginTop:40}]} 
          textAlignVertical="center"
          placeholderTextColor="#a8afc3"
          placeholder="请输入您的手机号码" 
          multiline={false}
          keyboardType="numeric"
          maxLength = {11}
          textContentType="telephoneNumber"
          underlineColorAndroid="#007eff"
          />

        <View style={{position:"relative"}}>
          <TextInput 
            style={[loginStyles.input]} 
            textAlignVertical="center"
            placeholderTextColor="#a8afc3" 
            placeholder="请输入短信验证码" 
            textContentType="password"
            underlineColorAndroid="#a8afc3"/>
            <Text 
              style={{
                color:"#007eff",
                fontSize:14,
                position:"absolute",
                right:15,bottom:25,height:(this.state.isShowVerificationTv ? 50 : 0),
                textAlignVertical:"bottom",}} 
              onPress={()=>{this.setState({defaultAnimationModal:true})}}>获取验证码</Text>
          

        </View>
        {/* 开启医联 */}
        <RoundButton/>
        <View style={{flexDirection:"row",marginTop:50}}>

          <TouchableOpacity style={{flex:1}}      
              onPress={()=>{
                  console.log("view is click");
                  this.setState({
                    isShowVerificationTv:true
                  })
                }}>
            <View style={{flexDirection:"column",alignItems:"center"}}>
              <Image source={require("./img/login_invitation_code.png")}/>
              <Text style={loginStyles.loginType}>验证码登录</Text>
            </View>
          </TouchableOpacity>



          <TouchableOpacity       
            onPress={()=>{
                console.log("view is click");
                this.setState({
                  isShowVerificationTv:false
                })
              }}>
      
            <View 
              style={{flexDirection:"column",flex:1,alignItems:"center"}}>
              <Image source={require("./img/login_password.png")}/>
              <Text 
                style={loginStyles.loginType}>密码登录</Text>
            </View>
          </TouchableOpacity>


          <View style={{flexDirection:"column",flex:1,alignItems:"center"}}>
            <Image source={require("./img/login_one_key.png")}/>
            <Text style={loginStyles.loginType}>一件登录</Text>
          </View>
        </View>
        {this.verificationDialog()}
      </View>
    );
  }

  componentWillMount(){
    this._panResponder = PanResponder.create({
      // 单击手势是否可以成为响应者
      onStartShouldSetPanResponder:(evt,gs) => true,
      // 移动手势是否可以成为响应者
      onMoveShouldSetPanResponder:  (evt, gs) => true,
    // 与onStartShouldSetPanResponder相同，当此组件A里包含了子组件B也为触摸事件响应者时，若此时设为true，则父组件A优先级更高
      onStartShouldSetPanResponderCapture: (evt, gestureState) => {
        return false
      },
      // 拦截子组件的移动手势传递,是否拦截
      onMoveShouldSetPanResponderCapture: (evt, gs) => true,
      // 单击手势监听回调
      onPanResponderGrant: (evt, gs) => {
        console.log(`onPanResponderGrant call: gs=` ,gs);
        l = this.state.lastMarginLeft + gs.dx;
        l = l <= 22 ? 22 : l;
        this.setState({
          marginLeft:l,
        })
      },
      // 手势申请失败,未成为响应者的回调
      onResponderReject: (e) => {
        // 申请失败,其他组件未释放响应者
        console.log('onResponderReject==>' + '响应者申请失败')
      },
      // 移动手势监听回调
      onPanResponderMove: (evt, gs) => {
        console.log(`gs.X : ${gs.dx}   Y : ${gs.dy}`);
        l = this.state.lastMarginLeft + gs.dx;
        l = l <= 22 ? 22 : l;
        max = Dimensions.get('screen').width * 0.8 - 22 - 97;
        l = l >= max ? max : l;

        v = (l >= (Dimensions.get('screen').width * 0.8 - 23 - 97)) ? true : false;
        if (v) {
          ToastAndroid.show("验证成功!",ToastAndroid.SHORT);
        }

        this.setState({
          marginLeft:l,
          defaultAnimationModal:!v
        })
      },
      //手势开始
      onPanResponderStart:(evt, gs) => {

      },
      // 手势动作结束回调
      onPanResponderEnd: (evt, gs) => {
        console.log(`onPanResponderEnd call: gs=` ,gs);
        this.setState({
          lastMarginLeft: this.state.marginLeft,
        })
      },
      // 手势释放, 响应者释放回调
      onPanResponderRelease: (evt, gs) => {
        console.log(`onPanResponderRelease call: gs=` ,gs);
      },
       // 当前手势被强制取消的回调
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
        // 返回一个布尔值，决定当前组件是否应该阻止原生组件成为JS响应者
        // 默认返回true。目前暂时只支持android。
        return true;
      },
    })
  }

  verificationDialog(){
    return(
      <View style={{position:"absolute"}}>
        <Modal 
          width={0.8}
          rounded={false}
          actionsBordered
          visible={this.state.defaultAnimationModal}
          onTouchOutside={()=>{this.setState({
            defaultAnimationModal:false,
            marginLeft:20,
            lastMarginLeft:20})}}>

            <View style={{flexDirection:"column"}}> 
              <View>
                <Image style={loginStyles.icon} source={require("./img/food05.jpeg")}/>
                <View  style={{width:60,height:60, position:"absolute",top:75,right:60,backgroundColor:"#ffffff"}}/>
                <Image 
                  style={{width:60,height:60, position:"absolute",top:75,left:(this.state.marginLeft)}} 
                  source={require("./img/food06.jpeg")}/>
              </View>
   
              <Text style={{textAlign:"center",marginTop:19, marginBottom:15,color:"#000000"}}>拖动滑块，把图片拼合就验证成功啦!</Text>
              <View>
                <View style={{height:38, backgroundColor:"#ffcccc",marginLeft:16,marginRight:16,marginBottom:16, borderRadius:25}}/>
                <Text style={{textAlign:"center",
                  textAlignVertical:"center", 
                  backgroundColor:"#f35656", 
                  width:97,height:30,marginLeft:this.state.marginLeft,
                  marginRight:16,marginTop:4,marginLeft:this.state.marginLeft,
                  color:"#ffffff",
                  position:"absolute",
                  // transform:[{translateX:this.state.boxX}],
                  borderRadius:15}} 
                  {...this._panResponder.panHandlers}>拖动滑块</Text>
              </View>
            </View>
        </Modal>
      </View>
    )
  }
}

const loginStyles = StyleSheet.create({
  topTitle:{
    fontSize: 16,
    marginTop:8
  },
  loginText: {
    fontSize:12,
    color:"#7C7C86"
  },
  icon: {
    width:WINDOW_WIDTH,
    height: 210,
  },
  iconRect: {
    width:60,
    height: 60,
  },
  input:{
    paddingTop: 15,
    paddingBottom: 25,
    fontSize:14,
  },
  loginType:{
    textAlign:"center",
    color:"#7C7C86",
    fontSize:12,
    marginTop:16,
  }
})
