# react-native-android-circular-reveal

A circular reveal component for android in React Native. Based on <a href="https://github.com/ozodrukh/CircularReveal">Circular Reveal</a>.

<img src="https://media.giphy.com/media/hBOk4zDKp0RgY/giphy.gif" alt="Circular Reveal" style="max-width:100%;">

# Installation

<code>$ yarn add react-native-android-circular-reveal</code>

### Option: Manually

* Edit `android/settings.gradle` to look like this (without the +):

  ```diff
  rootProject.name = 'MyApp'

  include ':app'

  + include ':react-native-android-circular-reveal'
  + project(':react-native-android-circular-reveal').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-android-circular-reveal/android')
  ```

* Edit `android/app/build.gradle` (note: **app** folder) to look like this: 

  ```diff
  apply plugin: 'com.android.application'

  android {
    ...
  }

  dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile "com.android.support:appcompat-v7:23.0.1"
    compile "com.facebook.react:react-native:+"  // From node_modules
  + compile project(':react-native-android-circular-reveal')
  }
  ```

* Edit your `MainApplication.java` (deep in `android/app/src/main/java/...`) to look like this (note **two** places to edit):

  ```diff
  package com.myapp;

  + import com.ibrahim.ReactAndroidCircularRevealPackage;

  ....

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
        new MainReactPackage()
  +   , new ReactAndroidCircularRevealPackage()
      );
    }

  }
  ```

# Usage

### Example with Toolbar (Trying to achieve something like whatsapp search)
```javascript
import AndroidCircularReveal from 'react-native-android-circular-reveal';

import {
    StyleSheet
} from 'react-native';

import ExtraDimensions from 'react-native-extra-dimensions-android';

let screen_width = ExtraDimensions.get('REAL_WINDOW_WIDTH');

const Styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#FFF'
    },
    toolbar: {
        height: 56,
        backgroundColor: '#AE9156'
    },
    searchToolbarContainer: {
        flex: 0,
        width: screen_width,
        height: 56,
        position: 'absolute',
        top: 0,
        zIndex: 4
    },
    searchToolbar: {
        height: 56,
        backgroundColor: '#FFF',
        elevation: 4,
        borderBottomWidth: 8,
        borderBottomColor: 'rgba(0, 0, 0, 0.54)'
    },
    searchText: {
        fontFamily: 'Roboto-Regular',
        fontSize: 16,
        color: 'rgba(0,0,0, 0.87)',
        flex: 0,
        height: 56,
        width: screen_width - 72
    }
});

revealSearchToolBar() {
    this.refs['search-toolbar'].reveal(40);
};

hideSearchToolBar() {
    this.refs['search-toolbar'].hide(40);
};

render() {
    return (
        <View style={Styles.container}>
            <StatusBar backgroundColor='#9F854F' />
            <ToolbarAndroid
                style={Styles.toolbar}
                title="New group"
                titleColor={'#FFF'}
                subtitle="Add friends"
                subtitleColor={'#FFF'}
                navIcon={images.arrowBack}
                onIconClicked={() => this.dismissModal()}
                actions={[{showWithText: false, title: '', icon: images.search, show: 'always'}]}
                onActionSelected={() => this.revealSearchToolBar()}>
            </ToolbarAndroid>
            <AndroidCircularReveal 
                ref="search-toolbar"
                style={Styles.searchToolbarContainer}
                animationDuration={400}>
                <ToolbarAndroid
                    navIcon={images.arrowBackBlack}
                    onIconClicked={() => this.hideSearchToolBar()}
                    style={Styles.searchToolbar}>
                    <View style={{flex: 1, flexDirection: 'row'}}>
                        <TextInput style={Styles.searchText} autoFocus={true} placeholder="Search" underlineColorAndroid={'transparent'} autoCorrect={false} />
                    </View>
                </ToolbarAndroid>
            </AndroidCircularReveal>
        </View>
    );
};
```

# Properties

<table>
<thead>
<tr>
<th>Prop</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td><strong><code>animationDuration</code></strong></td>
<td>Used to set reveal animation speed.</td>
</tr>
</tbody></table>

# License

<a href="http://opensource.org/licenses/mit-license.html">MIT License</a>. Circular Reveal is under MIT license. © Ibrahim Ahmed 2017 - now
