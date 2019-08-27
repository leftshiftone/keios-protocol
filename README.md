[![CircleCI branch](https://img.shields.io/circleci/project/github/leftshiftone/keios-protocol/master.svg?style=flat-square)](https://circleci.com/gh/leftshiftone/keios-protocol)
[![GitHub tag (latest SemVer)](https://img.shields.io/github/tag/leftshiftone/keios-protocol.svg?style=flat-square)](https://github.com/leftshiftone/keios-protocol/tags)

# keios-protocol

## Java
`$ flatc -j spacy_incoming.fbs spacy_outgoing.fbs`

### Release

Releases are triggered locally. Just a tag will be pushed and CI pipelines take care of the rest.

Releases are triggered per module (e.g. keios-protocol-spacy) and for each language (i.e. java and python).

#### Major
Run `poetry run trigger-release-major {module}` locally.

#### Minor
Run `poetry run trigger-release-minor {module}` locally.
 
e.g. `poetry run trigger-release-minor keios-protocol-spacy` in order to release module keios-protocol-spacy

#### Patch
Run `poetry run trigger-release-patch {module}` locally.
