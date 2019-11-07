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
  Animated} from "react-native";

export default class Login extends Component{

  state = {
    defaultAnimationModal: false,
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
          underlineColorAndroid="#007eff"/>

        <View style={{position:"relative"}}>
          <TextInput 
            style={[loginStyles.input]} 
            textAlignVertical="center"
            placeholderTextColor="#a8afc3" 
            placeholder="请输入短信验证码" 
            underlineColorAndroid="#a8afc3"/>
      
          <Text style={{color:"#007eff",fontSize:14,position:"absolute",right:15,bottom:25}} onPress={()=>{this.setState({defaultAnimationModal:true})}}>获取验证码</Text>
        </View>
        <RoundButton/>
        <View style={{flexDirection:"row",marginTop:50}}>
          <View style={{flexDirection:"column",flex:1,alignItems:"center"}}>
            <Image source={require("./img/login_invitation_code.png")}/>
              <Text style={loginStyles.loginType}>验证码登录</Text>
          </View>

          <View style={{flexDirection:"column",flex:1,alignItems:"center"}}>
            <Image source={require("./img/login_password.png")}/>
            <Text style={loginStyles.loginType}>密码登录</Text>
          </View>

          <View style={{flexDirection:"column",flex:1,alignItems:"center"}}>
            <Image source={require("./img/login_one_key.png")}/>
            <Text style={loginStyles.loginType}>一件登录</Text>
          </View>
        </View>
        {this.verificationDialog()}
      </View>
    );
  }

  verificationDialog(){
    return(
      <View style={{position:"absolute"}}>
        <Modal 
          width={0.9}
          rounded
          actionsBordered
          visible={this.state.defaultAnimationModal}
          onTouchOutside={()=>{
            this.setState({defaultAnimationModal:false})
          }}
          modalTitle={
            <ModalTitle
              title="Popup Modal - Default Animation"
              align="left"
            />
          }>
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
