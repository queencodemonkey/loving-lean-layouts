# Android: Loving Lean Layouts

Example code and application for Huyen Tue Dao's talk on optimizing Android layouts.

## Application Structure

The launch screen of the application displays a list of examples for each section of the talk.
Each section represents an individual tool or topic for layout optimization.

## Sections (WIP)

1. Hierarchy Viewer: a complex layout containing several examples of `Views` and `ViewGroups` that could be optimized and that help demonstrate the use of the SDK Hierarchy Viewer.
2. Lint warnings: XML layouts that cause Lint warnings and that should generally be fixed when found.
3. Attributes: examples of using attributes/methods to consolidate multiple `Views` into a single `View.`
4. Simpler Views: examples of both using both Android features and selecting the most appropriate layout to reduce number of `Views` and flatten hierarchies.
5. Custom `ViewGroup`: example of using a custom `ViewGroup` to bypass double-layout passes from platform layouts such as the `RelativeLayout`. This example basically re-implements the launch screen (which uses `RelativeLayout` instances for each list item) with a custom `ViewGroup`. Some of the code in the `CustomListItem` was taken from Sriram Ramani's [Custom ViewGroups](https://sriramramani.wordpress.com/2015/05/06/custom-viewgroups/).

## Todo

1. Document code examples to distinguish the "bad practice" from the "good practice".
2. Finish implementing a fixed version of the "Hierarchy Viewer" layout.
3. Add back in the examples for inflation and `ViewStub`.
