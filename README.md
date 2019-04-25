[![CircleCI branch](https://img.shields.io/circleci/project/github/leftshiftone/keios-protocol/master.svg?style=flat-square)](https://circleci.com/gh/leftshiftone/keios-protocol)
[![GitHub tag (latest SemVer)](https://img.shields.io/github/tag/leftshiftone/keios-protocol.svg?style=flat-square)](https://github.com/leftshiftone/keios-protocol/tags)

# keios-protocol

## Java
`$ flatc -j spacy_incoming.fbs spacy_outgoing.fbs`

#### Release

Releases are triggered locally. Just a tag will be pushed and CI pipelines take care of the rest.

##### Major
Run `./gradlew final -x bintrayUpload -Prelease.scope=major` locally.

##### Minor
Run `./gradlew final -x bintrayUpload -Prelease.scope=minor` locally.

##### Patch
Run `./gradlew final -x bintrayUpload -Prelease.scope=patch` locally.
 
## Python
`$ flatc -p spacy_incoming.fbs spacy_outgoing.fbs`

#### Publish 
Currently none. Use: `$ pip install -e "git+https://github.com/leftshiftone/keios-protocol.git#egg=keios-protocol-0.4.0&subdirectory=python"`