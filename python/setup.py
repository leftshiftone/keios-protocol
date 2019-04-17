#!/usr/bin/env python
from setuptools import setup, find_packages

# dependencies
install_requires = [
      "flatbuffers==1.10"
]
extras_require = {}

VERSION = "0.3.0"

setup(name='keios-protocol',
      version=VERSION,
      description="keios-protocol",
      long_description="tbd",
      author='Leftshift One',
      author_email='devs@leftshift.one',
      url='http://www.leftshift.one/',
      license='beerware',
      packages=find_packages(exclude=['ez_setup', 'examples',
                                      'tests', 'tests.*', 'release']),
      include_package_data=True,
      zip_safe=False,
      install_requires=install_requires,
      extras_require=extras_require
)
