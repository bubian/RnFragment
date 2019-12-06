/** @format */

import {AppRegistry} from 'react-native';
import {name as appName} from './app.json';
import setup from './js/setup'
import input from './js/component/InputTextView'
import bar from './js/home/AppHome'
import login from './js/login/login'
import gesture from './js/gesture/gesture'

AppRegistry.registerComponent(appName, () => login);
