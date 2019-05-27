#!/usr/bin/env python
from setuptools import setup, find_packages

# dependencies
install_requires = [
      "flatbuffers==1.11"
]
extras_require = {}

VERSION = "0.6.0"

setup(name='keios-protocol',
      version=VERSION,
      description="keios-protocol",
      long_description="tbd",
      author='Leftshift One',
      author_email='devs@leftshift.one',
      url='http://www.leftshift.one/',
      license='The Apache License, Version 2.0',
      packages=find_packages(exclude=['ez_setup', 'examples',
                                      'tests', 'tests.*', 'release']),
      include_package_data=True,
      zip_safe=False,
      install_requires=install_requires,
      extras_require=extras_require
)
