# This script is used as a temporary workaround until poetry adds full scripting support
import os
import sys
from subprocess import check_call

import toml

PROJECTS_DIR_PREFIX = "keios-protocol-"


def install():
    command_ = ["poetry", "install"]
    if len(sys.argv) <= 1 or sys.argv[1] is None:
        exec_in_sub_modules(command_)
    else:
        exec_in_module(command_, sys.argv[1])


def test():
    command_ = ["poetry", "run", "pytest"]
    if len(sys.argv) <= 1 or sys.argv[1] is None:
        exec_in_sub_modules(command_)
    else:
        exec_in_module(command_, sys.argv[1])


def test_ci():
    exec_in_sub_modules(["poetry", "run", "pytest", '--junit-xml=$TEST_RESULTS_PATH/TEST-@filename@-junit.xml'])


def build():
    command_ = ["poetry", "build"]
    if len(sys.argv) <= 1 or sys.argv[1] is None:
        exec_in_sub_modules(command_)
    else:
        exec_in_module(command_, sys.argv[1])


def publish():
    exec_in_sub_modules(["poetry", "publish"])


def publish_ci():
    command_ = ["poetry", "publish", "-u", "$PYPI_USERNAME", "-p", "$PYPI_PASSWORD"]
    if len(sys.argv) <= 1 or sys.argv[1] is None:
        exec_in_sub_modules(command_)
    else:
        exec_in_module(command_, sys.argv[1])


def trigger_release_major():
    trigger_release("major")


def trigger_release_minor():
    trigger_release("minor")


def trigger_release_patch():
    trigger_release("patch")


def trigger_release(scope="minor"):
    module = sys.argv[1:][0]
    if module is None:
        raise ValueError("Module must be specified")
    tag_name = f"release-{scope}-{module}"
    check_call(["git", "pull", "--tags"])
    check_call(["git", "tag", tag_name])
    check_call(["git", "push", "--tags"])
    check_call(["git", "tag", "-d", tag_name])


def cleanup_release():
    tag_name = sys.argv[1:][0]
    if tag_name is None:
        raise ValueError("Tag must be specified")
    check_call(["git", "tag", "-d", tag_name])
    check_call(["git", "push", "-d", "origin", tag_name])


def release():
    tag_name = sys.argv[1:][0]
    if tag_name is None:
        raise ValueError("Tag must be specified")
    scope = tag_name.split("-")[1]
    module = tag_name.replace(f"release-{scope}-", "")

    check_call(["git", "pull", "--tags"])
    check_call(["poetry", "version", "minor"])
    check_call(["git", "add", "*.toml"])
    command = ["poetry", "version", scope]
    exec_in_module(command, module)
    check_call(["git", "add", f"{module}/*.toml"])
    version = toml.load(f"{module}/pyproject.toml")["tool"]["poetry"]["version"]
    print(f"Releasing {module} {version}")
    check_call(["git", "commit", "-m", f"Release {module} {version}"])
    check_call(["git", "tag", "-a", "-m", f"Release {module} {version}", f"{module}-python-v{version}"])
    check_call(["git", "push"])
    check_call(["git", "push", "--tags"])


def exec_in_sub_modules(command):
    root_dir = os.path.dirname(os.path.realpath(__file__))
    for filename in os.listdir(root_dir):
        if os.path.isdir(filename) and filename.startswith(PROJECTS_DIR_PREFIX):
            exec_in_module(command, filename)


def exec_in_module(command, module=None):
    cmd = command.copy()
    for e in cmd:
        if e.__contains__("@filename@"):
            cmd[cmd.index(e)] = e.replace("@filename@", module)
    print(f"Executing for module {module}: {' '.join(cmd)}")
    for e in cmd:
        if e.startswith("$"):
            cmd[cmd.index(e)] = os.getenv(e.replace("$", ""))
    check_call(cmd, cwd=module, env=os.environ)
