# keios-protocol

## Java compiler
flatc -j spacy_incoming.fbs spacy_outgoing.fbs

## Python compiler
flatc -p spacy_incoming.fbs spacy_outgoing.fbs

## Publish
`./gradlew clean build bintrayUpload -Puser=USER -Pkey=KEY`