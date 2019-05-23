/** @format */

import {AppRegistry} from 'react-native';
import {name as appName} from './app.json';
import setup from './js/page/setup'
import input from './js/component/InputTextView'
import bar from './js/page/HomeActivity'

AppRegistry.registerComponent(appName, () => bar);
