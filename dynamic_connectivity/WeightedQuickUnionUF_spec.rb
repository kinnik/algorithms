require_relative 'WeightedQuickUnionUF'

describe WeightedQuickUnionUF do
  let(:uf) { WeightedQuickUnionUF.new(10) }

  it 'should connect components' do

    uf.union(4,3)
    uf.union(3,8)
    uf.union(6,5)
    uf.union(9,4)
    uf.union(2,1)
    uf.union(5,0)
    uf.union(7,2)
    uf.union(6,1)
    uf.union(7,3)

    uf.count.should eq 1

    uf.tree_size.should eq [1, 1, 3, 1, 4, 1, 10, 1, 1, 1]

    # without path compression
    #uf.id.should eq [6, 2, 6, 4, 6, 6, 6, 2, 4, 4]

    # with path compression
    uf.id.should eq [6, 2, 6, 4, 6, 6, 6, 6, 4, 4]
  end

end
