import React, { Component } from 'react';
import { Platform, StyleSheet, Text, View, TextInput, Button, Alert } from 'react-native'

export default class InputTextView extends Component{
    constructor(props){
        super(props);
        this.state = {
            InputTextValue: '',
            ErrorStatus : true,
        }
    }

    onEnterText = (InputTextValue) => {
        if(InputTextValue.trim() != 0){
            this.setState({InputTextValue: InputTextValue,ErrorStatus : true});
        }else{
            this.setState({InputTextValue: InputTextValue,ErrorStatus : false});
        }
    }

    buttonClickListener = () =>{
        const { TextInputValue }  = this.state ;
        if (TextInputValue == ""){
           Alert.alert("Please enter the text to proceed");
        }
    }

    render() {
        return (
            <View style={styles.container}>
    
              <TextInput
                style={{height: 45,width: "95%",borderColor: "gray",borderWidth: 2}}
                // Adding hint in TextInput using Placeholder option.
                placeholder=" Enter Your User Name"
                //set the value in state.
                onChangeText={TextInputValue => this.onEnterText(TextInputValue)}
                // Making the Under line Transparent.
                underlineColorAndroid="transparent"
              />
    
    
              { this.state.ErrorStatus == false ? (
                 <Text style={styles.errorMessage}>
                   * Please enter the text to proceed.
                 </Text>
                ) : null  }
    
              <View style={[{ width: "93%", margin: 15, backgroundColor: "red" }]}>
                <Button
                onPress={this.buttonClickListener}
                title="Submit"
                color="#00B0FF"
                />
              </View>
    
            </View>
          );
    }
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      justifyContent: "center",
      alignItems: "center",
      backgroundColor: "#e5e5e5"
    },
    errorMessage: {
      fontSize: 20,
      color:"red",
      marginLeft:-80,
    }
  });