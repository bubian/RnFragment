import React,{Component} from "react";
// npm install react-native-vector-icons --save
// react-native link react-native-vector-icons
import FontAwesome from "react-native-vector-icons/FontAwesome";
// npm install react-navigation-material-bottom-tabs react-native-paper
import {createMaterialBottomTabNavigator} from 'react-navigation-material-bottom-tabs';
// npm install --save react-navigation
import { createAppContainer } from "react-navigation";

const tabBarIcon = name => ({tintColor}) => (
  <FontAwesome
    style={{backgroundColor: "transparent"}}
    name={name}
    color={tintColor}
    size={22}
  />
);

class ExpenseScreen extends React.Component {
  static navigationOptions = {
    tabBarIcon: tabBarIcon("shopping-cart"),
    tabBarLabel:"消费"
  };
  render() {
    return (
      <View>
        <Text>VAEMC</Text>
        <Text>消费</Text>
      </View>
    );
  }
}

class IncomeScreen extends React.Component {
  static navigationOptions = {
    tabBarIcon: tabBarIcon("credit-card"),
    tabBarLabel:"收入"
  };
  render() {
    return (
      <View>
        <Text>VAEMC</Text>
        <Text>收入</Text>
      </View>
    );
  }
}
class MyScreen extends React.Component {
  static navigationOptions = {
    tabBarIcon: tabBarIcon("user"),
    tabBarLabel:"我的"
  };
  render() {
    return (
      <View>
        <Text>VAEMC</Text>
        <Text>我的</Text>
      </View>
    );
  }
}

export default createMaterialBottomTabNavigator(
  {
    Expense: {screen: ExpenseScreen},
    Income: { screen: IncomeScreen },
    My: { screen: MyScreen },
  },
  {
    initialRouteName: 'Album',
    activeColor: '#f0edf6',
    inactiveColor: '#3e2465',
    barStyle: { backgroundColor: '#694fad' },
  }
);