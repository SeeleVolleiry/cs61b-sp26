# Using Git

```git
git init
git add <file_name>
git commit -m "commit_message"
git pull <repo_name> 拉取仓库
git push origin main 

git restore

git branch <new_branch_name>
git swirch <new_branch_name>
git branch -d <branch_name>

git merge <branch_name>
git rebase <branch_name>
```

# Learning Git Branching

git的的提交就像是一棵节点树，不同的命令会对节点树或者是当前节点指针执行操作。

## git commit:提交

git commit -m "commit message"


## git branch:分支

git branch <new_branch_name>：创建新分支
git switch <new_branch_name>: 切换到指定的分支


## 合并分支

两种方法：一种是`git merge <merged_branche_name>` ，`git rebase`


Rebase 实际上就是取出一系列的提交记录，“复制”它们，然后在另外一个地方逐个的放下去。

Rebase 的优势就是可以创造更线性的提交历史，这听上去有些难以理解。如果只允许使用 Rebase 的话，代码库的提交历史将会变得异常清晰。

## 在提交树上移动

我们首先看一下 “HEAD”。 HEAD 是当前所在提交记录的符号名称 —— 它本质上标识着你正在其上工作的那个提交。

HEAD 总是指向当前分支上最近一次提交记录。大多数修改提交树的 Git 命令都是从改变 HEAD 的指向开始的。

HEAD 通常情况下是指向分支名的（如 bugFix）。在你提交时，bugFix 的状态会被改变，且这一变化通过 HEAD 可见。

如果想看 HEAD 指向，可以通过 cat .git/HEAD 查看， 如果 HEAD 指向的是一个符号引用（即分支名而非提交的哈希值

## 远程仓库

远程仓库并不复杂, 在如今的云计算盛行的世界很容易把远程仓库想象成一个富有魔力的东西, 但实际上它们只是你的仓库在另个一台计算机上的拷贝。你可以通过因特网与这台计算机通信 —— 也就是增加或是获取提交记录

话虽如此, 远程仓库却有一系列强大的特性

首先也是最重要的的点, 远程仓库是一个强大的备份。本地仓库也有恢复文件到指定版本的能力, 但所有的信息都是保存在本地的。有了远程仓库以后，即使丢失了本地所有数据, 你仍可以通过远程仓库拿回你丢失的数据。

还有就是, 远程让代码社交化了! 既然你的项目被托管到别的地方了, 你的朋友可以更容易地为你的项目做贡献(或者拉取最新的变更)
