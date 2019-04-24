# keios-protocol

## Java
`$ flatc -j spacy_incoming.fbs spacy_outgoing.fbs`

#### Publish 
`$ ./gradlew clean build bintrayUpload -Puser=USER -Pkey=KEY`

## Python
`$ flatc -p spacy_incoming.fbs spacy_outgoing.fbs`

#### Publish 
Currently none. Use: `$ pip install -e "git+https://github.com/leftshiftone/keios-protocol.git#egg=keios-protocol-0.4.0&subdirectory=python"`