<a name="v0.2.1"></a>

### v0.2.1 (2015-01-02)

#### Bug Fixes

* exclude demo code from final
  build ([5440d6fa](http://github.com/angular-ui/ui-utils/commit/8c91c5e1f4e43baf9bb910e39640586497ac06d0))

<a name="v0.2.0"></a>

## v0.2.0 (2014-12-31)

#### Bug Fixes

* **dep:** correct jquery 2.1.0 component
  path ([fd03855a](http://github.com/angular-ui/ui-utils/commit/fd03855ac336d00ce19685f4df90b862e2f5c9b4))
* **gh-pages:** wrong download
  link ([d575856e](http://github.com/angular-ui/ui-utils/commit/d575856e9ce575d40015d532d8a3684521f5d26d),
  closes [#186](http://github.com/angular-ui/ui-utils/issues/186))
* **scrollfix:** get scrollTop from
  scrollfix-target ([0724d1d4](http://github.com/angular-ui/ui-utils/commit/0724d1d41593d3d89ecd015026576570360f2f20))
* **travis:** fix jslint errors in
  mask.js ([41534729](http://github.com/angular-ui/ui-utils/commit/415347293d7200d8fc4a14b99e9744249e6c80da))

#### Features

* **scroll:** Rename ui-scroll* files to
  scroll* ([9dbb1b18](http://github.com/angular-ui/ui-utils/commit/9dbb1b185ac51bdce834405f7b43e514ad29d978))

#### Breaking Changes

* if you rely on ui-mask to invalidate an empty viewValue this will no longer work. Please use the required attribute or
  ng-required directive to specify whether the input is required.

fixes #198
([781e59e5](http://github.com/angular-ui/ui-utils/commit/781e59e5a1fa72db91eca6a257408ffe72da0c57))

<a name="v0.1.0"></a>

## v0.1.0 (2013-12-29)

#### Bug Fixes

* **mark:** TypeError: input is
  undefined ([5440d6fa](http://github.com/angular-ui/ui-utils/commit/5440d6fa8514ee86efc480b0abbf66cf244889ad))
* **publisher:**
    * don't throw error when 'dist/sub' don't
      exist ([bd319236](http://github.com/angular-ui/ui-utils/commit/bd31923668c0ea80311b9dbe7d72bfbe55956325))
    * rename sub componenet
      stuff ([5dcdc379](http://github.com/angular-ui/ui-utils/commit/5dcdc3794efe66112522415aafe9ebe965a274f6))
* **ui-scroll:**
    * 'newitems' is not
      defined. ([796e310a](http://github.com/angular-ui/ui-utils/commit/796e310a26ac43a248c0c732877242890fdda2be))
    * 'isArray' is not
      defined. ([3fd7fc47](http://github.com/angular-ui/ui-utils/commit/3fd7fc47de7d05460a55ca42e4afec60d8e8cc4d))
    * 'setOffset' is not
      defined. ([32140e04](http://github.com/angular-ui/ui-utils/commit/32140e04be176c4b2a5954d2cf8e9ec3c48a6f5c))

#### Features

* **alias:** Created a new ui-alias module for renaming/combining
  directives ([1582d54e](http://github.com/angular-ui/ui-utils/commit/1582d54ecaf81cb516a28368c0d409b5d5fe7da9))
* **grunt:**
    * add 'changelog'
      task ([b7fed5a6](http://github.com/angular-ui/ui-utils/commit/b7fed5a6026121d0098f892aa0a221c0d9c14d56),
      closes [#145](http://github.com/angular-ui/ui-utils/issues/145))
    * use Angular UI
      Publisher ([3c209713](http://github.com/angular-ui/ui-utils/commit/3c20971307e50741f88da21cb638077237e56da2),
      closes [#153](http://github.com/angular-ui/ui-utils/issues/153))
    * new 'serve'
      task ([a18ed32c](http://github.com/angular-ui/ui-utils/commit/a18ed32ce134acabe7adc79b41e82ed6c52109ed))
    * quality code more
      strict ([332ebff1](http://github.com/angular-ui/ui-utils/commit/332ebff1fdc7edf4d44d64f4796ec2f70e90947f))
    * use ngmin in the 'dist'
      task ([93ba905f](http://github.com/angular-ui/ui-utils/commit/93ba905fadfd4d0970d384f7978e19a3561cea65))
    * add ngmin build all subcomponents in
      dist/sub ([783140ab](http://github.com/angular-ui/ui-utils/commit/783140abe1b8d6c0f842eceb7fc24a0f16d73ca5))
* **publisher:**
    * change travis scripts to work with the component-publisher
      system ([12d97d3b](http://github.com/angular-ui/ui-utils/commit/12d97d3bf88da86875141093fc164f1537d0dfe2))
    * add and config component-publisher
      system ([4cea7ea5](http://github.com/angular-ui/ui-utils/commit/4cea7ea5bb4c47ad74c4f5123121a2896bf6f717))
* **travis:** add sub component auto
  publishing :) ([0d64db00](http://github.com/angular-ui/ui-utils/commit/0d64db00a5c50816cbf0b022aa5607fee29d5e2a))

