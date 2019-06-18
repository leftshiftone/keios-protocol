[![CircleCI branch](https://img.shields.io/circleci/project/github/leftshiftone/keios-protocol/master.svg?style=flat-square)](https://circleci.com/gh/leftshiftone/keios-protocol)
[![GitHub tag (latest SemVer)](https://img.shields.io/github/tag/leftshiftone/keios-protocol.svg?style=flat-square)](https://github.com/leftshiftone/keios-protocol/tags)

# keios-protocol

## Java
`$ flatc -j spacy_incoming.fbs spacy_outgoing.fbs`

### Release

Releases are triggered locally. Just a tag will be pushed and CI pipelines take care of the rest.

#### Major
Run `./gradlew final -x bintrayUpload -Prelease.scope=major` locally.

#### Minor
Run `./gradlew final -x bintrayUpload -Prelease.scope=minor` locally.

#### Patch
Run `./gradlew final -x bintrayUpload -Prelease.scope=patch` locally.
 
## Python
`$ flatc -p spacy_incoming.fbs spacy_outgoing.fbs`

### Release
Releases are triggered locally. Just a tag will be pushed to trigger the CI release pipeline.

**ATTENTION: Releasing via CI is not supported at the moment!**

#### Major
Run `poetry run trigger-release-major {module}` locally.
 
e.g. `poetry run trigger-release-major keios-protocol-spacy` in order to release module keios-protocol-spacy

#### Minor
Run `poetry run trigger-release-minor {module}` locally.

#### Patch
Run `poetry run trigger-release-patch {module}` locally.
